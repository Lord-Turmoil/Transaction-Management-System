/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 *    Filename: Test.java
 *
 * Last Update: 7/24/23, 4:56 PM
 */

import host.ConsoleHost;
import host.ConsoleHostBuilder;
import ioc.Container;
import tms.Startup;
import tms.model.TMSContext;
import tms.model.entity.User;
import tms.model.repo.UserRepository;

import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) {
        new Startup(Container.getGlobal()).configure(container -> {
            // logger and console
            var logger = Logger.getGlobal();
            container.register(Logger.class, logger);
            container.register(ConsoleHost.class, new ConsoleHostBuilder().setLogger(logger).build());
        }).configure(container -> {
            // database context
            var context = new TMSContext();
            container.register(TMSContext.class, context);
            // repository
            container.register(User.class, new UserRepository(context));
        }).configure(container -> {
            // services
        }).run();
    }
}
