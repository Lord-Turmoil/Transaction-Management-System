/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.exec.service.impl.util.ProductUtil;
import tms.exec.service.impl.util.ShopUtil;
import tms.model.entity.Commodity;
import tms.model.entity.Product;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.Globals;
import tms.shared.validator.impl.ProductNameValidator;

import java.math.BigDecimal;

public class CommodityService extends BaseService implements ICommodityService{
	public CommodityService(IContainer container) {
		super(container);
	}

	@Override
	public void release(int shopId, String name, BigDecimal price, int stock) throws ExecutionException {
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		if (user.role != User.Role.Merchant) {
			throw new ExecutionException(Errors.PermissionDenied);
		}

		var shopRepo = unitOfWork.getRepository(Shop.class);
		var shop = shopRepo.find(x -> x.id == shopId);	// may return null here
		if (!ShopUtil.isValidShop(shop, user)) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}
		if (stock > Globals.MAX_PRODUCT_STOCK) {
			throw new ExecutionException(Errors.IllegalProductCount);
		}

		var product = Product.create(name, price, user);
		unitOfWork.getRepository(Product.class).add(product);
		var commodity = Commodity.create(shop, stock, product);
		unitOfWork.getRepository(Commodity.class).add(commodity);

		printer.println("Put commodity success");
	}

	@Override
	public void release(int shopId, int productId, int stock) throws ExecutionException {
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		if (user.role != User.Role.Merchant) {
			throw new ExecutionException(Errors.PermissionDenied);
		}

		var shopRepo = unitOfWork.getRepository(Shop.class);
		var shop = shopRepo.find(x -> x.id == shopId);    // may return null here
		if (!ShopUtil.isValidShop(shop, user)) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}

		var productRepo = unitOfWork.getRepository(Product.class);
		var product = productRepo.find(x -> x.id == productId);
		if (!ProductUtil.isValidProduct(product, user)) {
			throw new ExecutionException(Errors.NoSuchProductId);
		}

		if (product.totalStock + stock > Globals.MAX_PRODUCT_STOCK) {
			throw new ExecutionException(Errors.IllegalProductCount);
		}

		var commodity = Commodity.create(shop, stock, product);
		unitOfWork.getRepository(Commodity.class).add(commodity);

		printer.println("Put commodity success");
	}

	@Override
	public void list(String id) throws ExecutionException {

	}

	@Override
	public void list(int shopId) throws ExecutionException {

	}

	@Override
	public void search(String name) throws ExecutionException {

	}
}
