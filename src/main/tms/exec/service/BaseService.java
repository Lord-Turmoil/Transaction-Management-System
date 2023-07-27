/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.model.entity.LoginStatus;
import tms.model.entity.User;
import tms.shared.Errors;
import uow.IUnitOfWork;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Logger;

public class BaseService implements IService {
	protected IContainer container;
	protected IUnitOfWork unitOfWork;
	protected Logger logger;
	protected PrintStream printer;

	public BaseService(IContainer container) {
		this.container = container;
		this.unitOfWork = container.resolveRequired(IUnitOfWork.class);
		this.printer = container.resolveRequired(PrintStream.class);
		this.logger = container.resolve(Logger.class);
	}

	protected LoginStatus getLoginStatus() {
		return container.resolveRequired(LoginStatus.class);
	}

	protected User getCurrentUser() {
		return getLoginStatus().getUser();
	}

	protected void setCurrentUser(User user) {
		getLoginStatus().setUser(user);
	}

	protected boolean isLoggedIn() {
		return getCurrentUser() != null;
	}

	protected User getRequiredUser() throws ExecutionException {
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		return user;
	}

	protected User getRequiredUser(User.Role role) throws ExecutionException {
		var user = getRequiredUser();
		if (user.role != role) {
			throw new ExecutionException(Errors.PermissionDenied);
		}
		return user;
	}

	protected User getRequiredUser(User.Role... roles) throws ExecutionException {
		var user = getRequiredUser();
		if (Arrays.stream(roles).noneMatch(x -> x == user.role)) {
			throw new ExecutionException(Errors.PermissionDenied);
		}
		return user;
	}

	protected boolean checkPermission(User.Role role) throws ExecutionException {
		var user = getRequiredUser();
		if (user.role != role) {
			return false;
		}
		return true;
	}

	protected boolean checkPermission(User.Role... roles) throws ExecutionException {
		var user = getRequiredUser();
		if (Arrays.stream(roles).noneMatch(x -> x == user.role)) {
			throw new ExecutionException(Errors.PermissionDenied);
		}
		return true;
	}
}
