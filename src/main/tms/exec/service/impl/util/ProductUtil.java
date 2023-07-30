/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import host.exec.ExecutionException;
import tms.model.entity.Commodity;
import tms.model.entity.Product;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.validator.impl.PriceValidator;
import uow.IRepository;

import java.math.BigDecimal;

public class ProductUtil {
	private ProductUtil() {}

	public static boolean isValidProduct(Product product, User user) {
		if (product == null || product.status == Product.Status.Unavailable) {
			return false;
		}
		return product.owner.equals(user);
	}

	public static int parseProductId(String productIdString) throws ExecutionException {
		try {
			return parseId(productIdString);
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalProductId);
		}
	}

	public static int parseStock(String stockString) throws ExecutionException {
		try {
			return Integer.parseInt(stockString);
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalProductCount);
		}
	}

	public static BigDecimal parsePrice(String priceString) throws ExecutionException {
		if (!new PriceValidator().check(priceString)) {
			throw new ExecutionException(Errors.IllegalProductPrice);
		}
		return new BigDecimal(priceString);
	}

	public static boolean hasAccessToCommodity(Commodity commodity, User user) {
		if (commodity == null || user == null) {
			throw new IllegalArgumentException();
		}
		return commodity.product.owner.equals(user) || (user.role == User.Role.Administrator);
	}

	public static Product getProduct(IRepository<Product> repo, int productId) throws ExecutionException {
		var product = repo.find(x -> x.id == productId);
		if (product == null) {
			throw new ExecutionException(Errors.NoSuchProductId);
		}
		return product;
	}

	public static Commodity getCommodity(IRepository<Commodity> repo, int shopId, int productId) throws ExecutionException {
		var commodity = repo.find(x -> (x.shop.id == shopId) && (x.product.id == productId));
		if (commodity == null) {
			throw new ExecutionException(Errors.NoSuchProductId);
		}
		return commodity;
	}

	public static Commodity getCommodity(IRepository<Commodity> repo, Shop shop, Product product) throws ExecutionException {
		return getCommodity(repo, shop.id, product.id);
	}

	private static int parseId(String value) throws NumberFormatException {
		if (!value.matches("^C-\\d+$")) {
			throw new NumberFormatException("Bad prefix");
		}
		return Integer.parseInt(value.substring(2));
	}
}
