/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IAccountService;
import tms.shared.Errors;

import java.util.List;

public class RegisterCommand extends BaseCommand {
    private final IAccountService service;

    public RegisterCommand(IContainer container, IAccountService service) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException {
        if (args.size() != 5) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        service.register(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4));
    }
}
