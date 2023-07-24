/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: BaseService.java
 * Last Update: 7/24/23, 5:30 PM
 */

package tms.service;

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

    public BaseService(IContainer container, IUnitOfWork unitOfWork, PrintStream printer, Logger logger) {
        this.container = container;
        this.unitOfWork = unitOfWork;
        this.printer = printer;
        this.logger = logger;
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
