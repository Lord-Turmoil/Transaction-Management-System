/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import host.exec.ExecutionException;
import tms.model.entity.Product;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.validator.impl.PriceValidator;

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
			return Product.parseId(productIdString);
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
}
