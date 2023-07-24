/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: Startup.java
 * Last Update: 7/24/23, 4:57 PM
 */

package tms;

import host.ConsoleHost;
import ioc.Container;
import ioc.IContainer;

import java.util.function.Consumer;

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
