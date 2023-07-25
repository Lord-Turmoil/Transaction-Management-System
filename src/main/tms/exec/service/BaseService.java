/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service;

import ioc.IContainer;
import tms.model.entity.LoginStatus;
import tms.model.entity.User;
import uow.IUnitOfWork;

import java.io.PrintStream;
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

    public LoginStatus getLoginStatus() {
        return container.resolveRequired(LoginStatus.class);
    }

    public User getCurrentUser() {
        return getLoginStatus().getUser();
    }

    public void setCurrentUser(User user) {
        getLoginStatus().setUser(user);
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }
}
