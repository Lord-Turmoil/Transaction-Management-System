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
		var id = args.get(0);
		if (!new IdValidator().check(id)) {
			throw new ExecutionException(Errors.IllegalId);
		}
		var name = args.get(1);
		if (!new UserNameValidator().check(name)) {
			throw new ExecutionException(Errors.IllegalName);
		}
		var password = args.get(2);
		if (!new PasswordValidator().check(password)) {
			throw new ExecutionException(Errors.IllegalPassword);
		}
		var confirm = args.get(3);
		User.Role role = switch (args.get(4)) {
			case "Administrator" -> User.Role.Administrator;
			case "Merchant" -> User.Role.Merchant;
			case "Customer" -> User.Role.Customer;
			default -> throw new ExecutionException(Errors.IllegalIdentity);
		};
		service.register(id, name, password, confirm, role);
	}
}
