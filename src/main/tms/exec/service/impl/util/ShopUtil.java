/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import tms.model.entity.Shop;
import tms.model.entity.User;

public class ShopUtil {
	private ShopUtil() {}

	public static boolean isValidShop(Shop shop, User user) {
		if (shop == null || shop.status == Shop.Status.Closed) {
			return false;
		}
		return shop.owner.equals(user);
	}
}
