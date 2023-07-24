/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: Test.java
 * Last Update: 7/24/23, 5:30 PM
 */

import host.ConsoleHost;
import host.ConsoleHostBuilder;
import ioc.Container;
import tms.Startup;
import tms.cmd.CommandHost;
import tms.cmd.CommandProvider;
import tms.model.TMSContext;
import tms.model.entity.LoginStatus;
import tms.model.entity.User;
import tms.model.repo.UserRepository;
import uow.IUnitOfWork;
import uow.UnitOfWork;

import java.io.PrintStream;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) {
        new Startup(Container.getGlobal()).configure(container -> {
            // logger & printer
            var logger = Logger.getGlobal();
            container.register(Logger.class, logger);
            var printer = System.out;
            container.register(PrintStream.class, printer);
            // command provider
            var provider = new CommandProvider(container);
            CommandHost.registerAll(provider);
            // console
            var builder = new ConsoleHostBuilder()
                    .setProvider(provider)
                    .setOutput(printer)
                    .setLogger(logger)
                    .setInteractive(true);
            container.register(ConsoleHost.class, builder.build());
        }).configure(container -> {
            // database context
            var context = new TMSContext();
            container.register(TMSContext.class, context);
            // unit of work
            container.register(IUnitOfWork.class, new UnitOfWork(container));
            // repository
            container.register(User.class, new UserRepository(context));
        }).configure(container -> {
            // services
        }).configure(container -> {
            // else
            container.register(LoginStatus.class, new LoginStatus());
        }).run();
    }
}
