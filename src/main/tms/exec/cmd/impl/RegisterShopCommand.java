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
import tms.shared.validator.impl.ShopNameValidator;

import java.util.List;

public class RegisterShopCommand extends BaseCommand {
	private final IShopService service;

	public RegisterShopCommand(IShopService service, IContainer container) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		if (args.size() != 1) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		var name = args.get(0);
		service.register(name);
	}
}
