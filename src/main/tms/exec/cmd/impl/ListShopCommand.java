/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IShopService;
import tms.shared.Errors;

import java.util.List;

public class ListShopCommand extends BaseCommand {
	private final IShopService service;

	public ListShopCommand(IShopService service, IContainer container) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		switch (args.size()) {
			case 0 -> service.list();
			case 1 -> service.list(args.get(0));
			default -> throw new ExecutionException(Errors.IllegalArgumentCount);
		}
	}
}
