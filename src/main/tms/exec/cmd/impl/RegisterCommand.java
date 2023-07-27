/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IAccountService;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.validator.impl.IdValidator;
import tms.shared.validator.impl.PasswordValidator;
import tms.shared.validator.impl.UserNameValidator;

import java.util.List;

public class RegisterCommand extends BaseCommand {
	private final IAccountService service;

	public RegisterCommand(IAccountService service, IContainer container) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		if (args.size() != 5) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		service.register(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4));
	}
}
