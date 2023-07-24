/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: CommandHost.java
 * Last Update: 7/24/23, 7:18 PM
 */

package tms.cmd;

import ioc.IContainer;
import tms.cmd.impl.ExitCommand;
import tms.cmd.impl.RegisterCommand;
import tms.service.impl.AccountService;
import tms.service.impl.ExitService;

/**
 * To register all classes.
 */
public class CommandHost {
    public static void registerAll(CommandProvider provider) {
        provider.register("exit", ExitCommand.class, ExitService.class);
        provider.register("register", RegisterCommand.class, AccountService.class);
    }
}
