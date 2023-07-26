/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.formatter.impl.PrintHandler;
import tms.shared.formatter.impl.UserInfoFormatter;

public class AccountService extends BaseService implements IAccountService {
	public AccountService(IContainer container) {
		super(container);
	}

	@Override
	public void register(String id, String name, String password, String confirm, User.Role role) throws ExecutionException {
		if (isLoggedIn()) {
			throw new ExecutionException(Errors.AlreadyLoggedIn);
		}

		var user = new User();

		var repo = unitOfWork.getRepository(User.class);
		if (repo.exists(x -> x.id.equals(id))) {
			throw new ExecutionException(Errors.DuplicatedId);
		}
		user.id = id;
		user.name = name;
		if (!password.equals(confirm)) {
			throw new ExecutionException(Errors.PasswordInconsistent);
		}
		user.password = password;
		user.role = role;

		// save to database
		repo.add(user);

		printer.println("Register success");
	}

	@Override
	public void login(String id, String password) throws ExecutionException {
		if (isLoggedIn()) {
			throw new ExecutionException(Errors.AlreadyLoggedIn);
		}

		var user = unitOfWork.getRepository(User.class).find(x -> x.id.equals(id));
		if (user == null) {
			throw new ExecutionException(Errors.NoSuchUser);
		}
		if (!user.password.equals(password)) {
			throw new ExecutionException(Errors.WrongPassword);
		}
		setCurrentUser(user);

		printer.println("Welcome to TMS");
	}

	@Override
	public void logout() throws ExecutionException {
		if (!isLoggedIn()) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}

		setCurrentUser(null);
		printer.println("Bye~");
	}

	@Override
	public void printInfo() throws ExecutionException {
		User user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		new PrintHandler(printer).print(user, new UserInfoFormatter());
	}

	@Override
	public void printInfo(String id) throws ExecutionException {
		User user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		if (user.role != User.Role.Administrator) {
			throw new ExecutionException(Errors.PermissionDenied);
		}
		var target = unitOfWork.getRepository(User.class).find(x -> x.id.equals(id));
		if (target == null) {
			throw new ExecutionException(Errors.NoSuchUser);
		}
		new PrintHandler(printer).print(target, new UserInfoFormatter());
	}
}
