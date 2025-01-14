/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

import host.ConsoleHost;
import host.ConsoleHostBuilder;
import ioc.Container;
import tms.Startup;
import tms.exec.cmd.CommandHost;
import tms.exec.cmd.CommandProvider;
import tms.exec.service.ServiceHost;
import tms.model.RepositoryHost;
import tms.model.TMSContext;
import tms.model.entity.LoginStatus;
import uow.IUnitOfWork;
import uow.UnitOfWork;

import java.io.PrintStream;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) {
        new Startup(new Container()).configure(container -> {
            // logger & printer
            var logger = Logger.getGlobal();
            container.addSingleton(Logger.class, logger);
            var printer = System.out;
            container.addSingleton(PrintStream.class, printer);
            // command provider
            var provider = new CommandProvider(container);
            CommandHost.registerAll(provider);
            // console
            var builder = new ConsoleHostBuilder()
                    .setProvider(provider)
                    .setOutput(printer)
                    .setLogger(logger)
                    .setInteractive(true);
            container.addSingleton(ConsoleHost.class, builder.build());
        }).configure(container -> {
            // database context
            var context = new TMSContext();
            container.addSingleton(TMSContext.class, context);
            // unit of work
            container.addSingleton(IUnitOfWork.class, new UnitOfWork(container));
            // repository
            RepositoryHost.registerAll(container, context);
            // Service (after unit of work)
            ServiceHost.registerAll(container);
        }).configure(container -> {
            // else
            container.addSingleton(LoginStatus.class, new LoginStatus());
        }).run();
    }
}

