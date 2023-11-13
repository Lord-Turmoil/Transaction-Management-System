/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.exec.service.impl.util.ProductUtil;
import tms.exec.service.impl.util.ShopUtil;
import tms.model.entity.*;
import tms.shared.Errors;
import tms.shared.Globals;
import tms.shared.formatter.impl.CommodityFormatter;
import tms.shared.formatter.impl.PrintHandler;
import tms.shared.validator.impl.IdValidator;
import tms.shared.validator.impl.ShopNameValidator;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class CommodityService extends BaseService implements ICommodityService {
    public CommodityService(IContainer container) {
        super(container);
    }

    @Override
    public void release(String shopIdString, String name, String priceString, String stockString) throws ExecutionException {
        var user = getRequiredUser(User.Role.Merchant);

        int shopId = ShopUtil.parseShopId(shopIdString);
        var shopRepo = unitOfWork.getRepository(Shop.class);
        var shop = shopRepo.find(x -> x.id == shopId);    // may return null here
        if (!ShopUtil.isValidShop(shop, user)) {
            throw new ExecutionException(Errors.NoSuchShopId);
        }

        if (!new ShopNameValidator().check(name)) {
            throw new ExecutionException(Errors.IllegalShopName);
        }

        BigDecimal price = ProductUtil.parsePrice(priceString);

        var product = Product.create(name, price, user);
        unitOfWork.getRepository(Product.class).add(product);

        int stock = ProductUtil.parseStock(stockString);
        if (stock > Globals.MAX_PRODUCT_STOCK) {
            throw new ExecutionException(Errors.IllegalProductCount);
        }

        var commodity = Commodity.create(shop, stock, product);
        unitOfWork.getRepository(Commodity.class).add(commodity);

        printer.println("Put commodity success (commodityId: " + commodity.product.getId() + ")");
    }

    @Override
    public void release(String shopIdString, String productIdString, String stockString) throws ExecutionException {
        var user = getRequiredUser(User.Role.Merchant);

        var shopId = ShopUtil.parseShopId(shopIdString);
        var shopRepo = unitOfWork.getRepository(Shop.class);
        var shop = shopRepo.find(x -> x.id == shopId);    // may return null here
        if (!ShopUtil.isValidShop(shop, user)) {
            throw new ExecutionException(Errors.NoSuchShopId);
        }

        var productId = ProductUtil.parseProductId(productIdString);
        var productRepo = unitOfWork.getRepository(Product.class);
        var product = productRepo.find(x -> x.id == productId);
        if (!ProductUtil.isValidProduct(product, user)) {
            throw new ExecutionException(Errors.NoSuchProductId);
        }

        int stock = ProductUtil.parseStock(stockString);
        if (product.totalStock + stock > Globals.MAX_PRODUCT_STOCK) {
            throw new ExecutionException(Errors.IllegalProductCount);
        }

        // find existing commodity in this shop
        var repo = unitOfWork.getRepository(Commodity.class);
        var commodity = repo.find(x -> x.shop.equals(shop) && x.product.equals(product));
        if (commodity == null) {
            commodity = Commodity.create(shop, stock, product);
            repo.add(commodity);
        } else {
            commodity.updateStock(stock);
        }

        unitOfWork.getRepository(Commodity.class).add(commodity);

        printer.println("Put commodity success (commodityId: " + commodity.product.getId() + ")");
    }

    @Override
    public void list() throws ExecutionException {
        var user = getRequiredUser();

        if (user.role == User.Role.Merchant) {
            listCommodity(user);
        } else {
            listCommodity();
        }
    }

    @Override
    public void listById(String id) throws ExecutionException {
        ensurePermission(User.Role.Administrator);

        if (!new IdValidator().check(id)) {
            throw new ExecutionException(Errors.IllegalId);
        }

        var target = unitOfWork.getRepository(User.class).find(x -> x.id.equals(id));
        if (target == null) {
            throw new ExecutionException(Errors.NoSuchUser);
        }
        if (target.role != User.Role.Merchant) {
            throw new ExecutionException(Errors.UserNotMerchant);
        }

        listCommodity(target);
    }

    @Override
    public void listByShop(String shopIdString) throws ExecutionException {
        var user = getRequiredUser();

        int shopId = ShopUtil.parseShopId(shopIdString);
        var shop = ShopUtil.getShop(unitOfWork.getRepository(Shop.class), shopId);
        if (shop.status == Shop.Status.Closed) {
            throw new ExecutionException(Errors.NoSuchShopId);
        }

        if (user.role == User.Role.Merchant && !shop.owner.equals(user)) {
            throw new ExecutionException(Errors.PermissionDenied);
        }

        listCommodity(shop);
    }

    @Override
    public void remove(String productIdString) throws ExecutionException {
        var user = getCurrentUser();
        if (user == null) {
            throw new ExecutionException(Errors.NotLoggedIn);
        }
        if (user.role == User.Role.Customer) {
            throw new ExecutionException(Errors.PermissionDenied);
        }

        int productId = ProductUtil.parseProductId(productIdString);
        var product = ProductUtil.getProduct(unitOfWork.getRepository(Product.class), productId);
        if (!product.owner.equals(user)) {
            throw new ExecutionException(Errors.NoSuchProductId);
        }

        var orderRepo = unitOfWork.getRepository(Order.class);
        if (orderRepo.exists(x -> x.commodity.product.id == productId && x.isActive())) {
            throw new ExecutionException(Errors.UnfinishedOrderExists);
        }

        // This product field is unique among all commodities that refer to it.
        product.status = Product.Status.Unavailable;
        unitOfWork.getRepository(Commodity.class).delete(x -> x.product.equals(product));

        printer.println("Remove commodity success");
    }

    @Override
    public void remove(String productIdString, String shopIdString) throws ExecutionException {
        var user = getCurrentUser();
        if (user == null) {
            throw new ExecutionException(Errors.NotLoggedIn);
        }
        if (user.role == User.Role.Customer) {
            throw new ExecutionException(Errors.PermissionDenied);
        }

        int productId = ProductUtil.parseProductId(productIdString);
        int shopId = ShopUtil.parseShopId(shopIdString);

        var shop = ShopUtil.getShop(unitOfWork.getRepository(Shop.class), shopId);
        if (!ShopUtil.hasAccessToShop(shop, user)) {
            throw new ExecutionException(Errors.NoSuchShopId);
        }

        var repo = unitOfWork.getRepository(Commodity.class);
        var commodity = ProductUtil.getCommodity(repo, shopId, productId);
        if (!ProductUtil.hasAccessToCommodity(commodity, user)) {
            throw new ExecutionException(Errors.NoSuchProductId);
        }

        var orderRepo = unitOfWork.getRepository(Order.class);
        if (orderRepo.exists(x -> x.commodity.equals(commodity) && x.isActive())) {
            throw new ExecutionException(Errors.UnfinishedOrderExists);
        }

        // do not change product status
        repo.delete(commodity);

        printer.println("Remove commodity success");
    }

    private void listCommodity() throws ExecutionException {
        var shops = unitOfWork.getRepository(Shop.class).getAll(Comparator.comparingInt(x -> x.id));
        for (var shop : shops) {
            listCommodity(shop);
        }
    }

    private void listCommodity(User user) throws ExecutionException {
        var shops = unitOfWork.getRepository(Shop.class).findAll(
                x -> x.owner.equals(user),
                Comparator.comparingInt(x -> x.id));
        for (var shop : shops) {
            listCommodity(shop);
        }
    }

    private void listCommodity(Shop shop) throws ExecutionException {
        var commodities = unitOfWork.getRepository(Commodity.class).find(x -> x.shop.equals(shop));
        new PrintHandler(printer).print(commodities, new CommodityFormatter());
    }

    @Override
    public void search(String name) throws ExecutionException {
        var user = getRequiredUser();

        List<Commodity> commodities;
        var repo = unitOfWork.getRepository(Commodity.class);
        Comparator<Commodity> orderBy = (a, b) -> (a.shop.id == b.shop.id) ? (a.product.id - b.product.id) : (a.shop.id - b.shop.id);
        if (user.role == User.Role.Merchant) {
            commodities = repo.findAll(x -> x.product.name.equals(name) && x.shop.owner.equals(user), orderBy);
        } else {
            commodities = repo.findAll(x -> x.product.name.equals(name), orderBy);
        }
        commodities.removeIf(x -> x.getStock() == 0);
        if (commodities.isEmpty()) {
            printer.println("Commodity not exists");
        } else {
            new PrintHandler(printer).print(commodities, new CommodityFormatter());
        }
    }
}
