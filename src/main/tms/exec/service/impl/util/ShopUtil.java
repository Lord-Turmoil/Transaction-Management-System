/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import host.exec.ExecutionException;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.shared.Errors;

public class ShopUtil {
	private ShopUtil() {}

	public static boolean isValidShop(Shop shop, User user) {
		if (shop == null || shop.status == Shop.Status.Closed) {
			return false;
		}
		return shop.owner.equals(user);
	}

	public static int parseShopId(String shopIdString) throws ExecutionException {
		try {
			return Shop.parseId(shopIdString);
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalShopId);
		}
	}

	public static boolean hasAccessToShop(Shop shop, User user) {
		if (shop == null || user == null) {
			throw new IllegalArgumentException();
		}
		return shop.owner.equals(user) || (user.role == User.Role.Administrator);
	}
}
