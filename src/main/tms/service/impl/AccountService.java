/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: AccountService.java
 * Last Update: 7/24/23, 6:13 PM
 */

package tms.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.model.entity.User;
import tms.service.BaseService;
import tms.shared.Errors;
import tms.validator.impl.IdValidator;
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
        var user = getCurrentUser();
        if (user != null) {
            throw new ExecutionException(Errors.AlreadyLoggedIn);
        }

        var id = args.get(1);
        if (!new IdValidator().check(id)) {
            throw new ExecutionException(Errors.IllegalId);
        }
        var repo = unitOfWork.getRepository(User.class);
        if (repo.find(x -> x.Id.equals(id)) != null) {
        }
        var name = args.get(0);
        var password = args.get(2);
        var confirm = args.get(3);
        var type = args.get(4);
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
