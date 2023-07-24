package tms;

import host.ConsoleHost;
import host.ConsoleHostBuilder;
import ioc.Container;
import tms.model.TMSContext;
import tms.model.entity.User;
import tms.model.repo.UserRepository;

import java.util.logging.Logger;

public class Startup {
    public static void configureIoC() {
        var container = Container.getGlobal();

        // console host
        var host = new ConsoleHostBuilder()
                .setLogger(Logger.getGlobal())
                .build();
        container.register(ConsoleHost.class, host);

        // database context
        var context = new TMSContext();
        container.register(TMSContext.class, context);

        // repository
        container.register(User.class, new UserRepository(context));
    }
}
