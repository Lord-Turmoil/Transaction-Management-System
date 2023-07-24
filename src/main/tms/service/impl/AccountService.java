/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: AccountService.java
 * Last Update: 7/24/23, 6:13 PM
 */

package tms.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.model.entity.Role;
import tms.model.entity.User;
import tms.service.BaseService;
import tms.shared.Errors;
import tms.validator.impl.IdValidator;
import tms.validator.impl.NameValidator;
import tms.validator.impl.PasswordValidator;
import uow.IUnitOfWork;

import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

public class AccountService extends BaseService implements IAccountService {
    public AccountService(IContainer container) {
        super(container);
    }

    @Override
    public void register(List<String> args) throws ExecutionException {
        if (args.size() != 5) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        if (isLoggedIn()) {
            throw new ExecutionException(Errors.AlreadyLoggedIn);
        }

        var user = new User();
        var id = args.get(0);
        if (!new IdValidator().check(id)) {
            throw new ExecutionException(Errors.IllegalId);
        }
        var repo = unitOfWork.getRepository(User.class);
        if (repo.exists(x -> x.Id.equals(id))) {
            throw new ExecutionException(Errors.DuplicatedId);
        }
        user.Id = id;

        var name = args.get(1);
        if (!new NameValidator().check(name)) {
            throw new ExecutionException(Errors.IllegalName);
        }
        user.Name = name;

        var password = args.get(2);
        if (!new PasswordValidator().check(password)) {
            throw new ExecutionException(Errors.IllegalPassword);
        }
        var confirm = args.get(3);
        if (!password.equals(confirm)) {
            throw new ExecutionException(Errors.PasswordInconsistent);
        }
        user.Password = password;

        var type = args.get(4);
        user.Role = switch (type) {
            case "Administrator" -> Role.Administrator;
            case "Merchant" -> Role.Merchant;
            case "Customer" -> Role.Customer;
            default -> throw new ExecutionException(Errors.IllegalIdentity);
        };

        // save to database
        repo.add(user);

        printer.println("Register success");
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
