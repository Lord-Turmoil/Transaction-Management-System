/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd;

import tms.exec.cmd.impl.*;
import tms.exec.service.impl.IAccountService;
import tms.exec.service.impl.ICommodityService;
import tms.exec.service.impl.IExitService;
import tms.exec.service.impl.IShopService;

/**
 * To register all classes.
 */
public class CommandHost {
	private CommandHost() {}

	public static void registerAll(CommandProvider provider) {
		provider.register("exit", ExitCommand.class, IExitService.class);
		provider.register("quit", ExitCommand.class, IExitService.class);
		provider.register("register", RegisterCommand.class, IAccountService.class)
				.register("login", LoginCommand.class, IAccountService.class)
				.register("logout", LogoutCommand.class, IAccountService.class)
				.register("printInfo", PrintInfoCommand.class, IAccountService.class);
		provider.register("registerShop", RegisterShopCommand.class, IShopService.class)
				.register("listShop", ListShopCommand.class, IShopService.class)
				.register("cancelShop", CancelShopCommand.class, IShopService.class);
		provider.register("putCommodity", ReleaseCommodityCommand.class, ICommodityService.class)
				.register("listCommodity", ListCommodityCommand.class, ICommodityService.class)
				.register("searchCommodity", SearchCommodityCommand.class, ICommodityService.class);
	}
}
