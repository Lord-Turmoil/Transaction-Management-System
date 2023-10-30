/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms;

import host.ConsoleHost;
import ioc.Container;
import ioc.IContainer;

import java.util.function.Consumer;

public class Startup {
	private final IContainer container;

	public Startup(IContainer container) {
		this.container = container;
	}

	public Startup configure(Consumer<IContainer> action) {
		action.accept(container);
		return this;
	}

	public void run() {
		container.resolve(ConsoleHost.class).run();
	}
}
