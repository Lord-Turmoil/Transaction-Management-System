/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IAccountService;
import tms.shared.Errors;
import tms.shared.validator.impl.IdValidator;

import java.util.List;

public class LoginCommand extends BaseCommand {
	private final IAccountService service;

	public LoginCommand(IContainer container, IAccountService service) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		if (args.size() != 2) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		service.login(args.get(0), args.get(1));
	}
}
