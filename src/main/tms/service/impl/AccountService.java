/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: AccountService.java
 * Last Update: 7/24/23, 5:28 PM
 */

package tms.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.service.BaseService;
import tms.shared.Errors;
import uow.IUnitOfWork;

import java.util.List;
import java.util.logging.Logger;

public class AccountService extends BaseService implements IAccountService {

    public AccountService(IContainer container, IUnitOfWork unitOfWork, Logger logger) {
        super(container, unitOfWork, logger);
    }

    @Override
    public void register(List<String> args) throws ExecutionException {
        if (args.size() != 5) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }

    }

    @Override
    public void login(List<String> args) throws ExecutionException {

    }

    @Override
    public void logout(List<String> args) throws ExecutionException {

    }

    @Override
    public void printInfo(List<String> args) throws ExecutionException {

    }
}
