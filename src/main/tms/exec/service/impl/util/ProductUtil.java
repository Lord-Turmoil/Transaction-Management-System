/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import tms.model.entity.Product;
import tms.model.entity.User;

public class ProductUtil {
	private ProductUtil() {}

	public static boolean isValidProduct(Product product, User user) {
		if (product == null || product.status == Product.Status.Unavailable) {
			return false;
		}
		return product.owner.equals(user);
	}
}
