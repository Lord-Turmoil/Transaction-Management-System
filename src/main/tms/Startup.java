package tms;

import host.ConsoleHost;
import host.ConsoleHostBuilder;
import ioc.Container;
import ioc.IContainer;
import tms.model.TMSContext;
import tms.model.entity.User;
import tms.model.repo.UserRepository;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

public class Startup {
    private IContainer container;

    public Startup(IContainer container) {
        this.container = container;
    }

    public Startup configure(Consumer<IContainer> action) {
        action.accept(container);
        return this;
    }

    public void run() {
        Container.getGlobal().resolve(ConsoleHost.class).run();
    }
}
