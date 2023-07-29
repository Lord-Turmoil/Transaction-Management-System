/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service;

import ioc.IContainer;
import tms.exec.service.impl.*;

public class ServiceHost {
	private ServiceHost() {}

	public static void registerAll(IContainer container) {
		container.addSingleton(IExitService.class, ExitService.class)
				.addSingleton(IAccountService.class, AccountService.class)
				.addSingleton(IShopService.class, ShopService.class)
				.addSingleton(ICommodityService.class, CommodityService.class);
	}
}
