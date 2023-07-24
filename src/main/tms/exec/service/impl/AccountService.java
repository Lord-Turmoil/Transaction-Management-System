/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: AccountService.java
 * Last Update: 7/24/23, 9:14 PM
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.model.entity.Role;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.formatter.impl.UserInfoFormatter;
import tms.shared.validator.impl.IdValidator;
import tms.shared.validator.impl.NameValidator;
import tms.shared.validator.impl.PasswordValidator;

import java.util.List;

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
        if (args.size() != 2) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        if (isLoggedIn()) {
            throw new ExecutionException(Errors.AlreadyLoggedIn);
        }

        var id = args.get(0);
        if (!new IdValidator().check(id)) {
            throw new ExecutionException(Errors.IllegalId);
        }

        var user = unitOfWork.getRepository(User.class).find(x -> x.Id.equals(id));
        if (user == null) {
            throw new ExecutionException(Errors.NoSuchUser);
        }
        var password = args.get(1);
        if (!user.Password.equals(password)) {
            throw new ExecutionException(Errors.WrongPassword);
        }

        setCurrentUser(user);
        printer.println("Welcome to TMS");
    }

    @Override
    public void logout(List<String> args) throws ExecutionException {
        if (args.size() > 0) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        if (!isLoggedIn()) {
            throw new ExecutionException(Errors.NotLoggedIn);
        }

        setCurrentUser(null);
        printer.println("Bye~");
    }

    @Override
    public void printInfo(List<String> args) throws ExecutionException {
        if (args.size() > 1) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        User user = getCurrentUser();
        if (user == null) {
            throw new ExecutionException(Errors.NotLoggedIn);
        }

        User target = null;
        if (args.size() == 0) {
            target = user;  // user is not null here
        } else {
            if (user.Role != Role.Administrator) {
                throw new ExecutionException(Errors.PermissionDenied);
            }
            var id = args.get(0);
            if (!new IdValidator().check(id)) {
                throw new ExecutionException(Errors.IllegalId);
            }
            target = unitOfWork.getRepository(User.class).find(x -> x.Id.equals(id));
            if (target == null) {
                throw new ExecutionException(Errors.NoSuchUser);
            }
        }

        new UserInfoFormatter().format(printer, target);
    }
}
