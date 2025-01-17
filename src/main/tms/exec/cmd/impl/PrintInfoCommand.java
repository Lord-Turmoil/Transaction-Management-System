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

public class PrintInfoCommand extends BaseCommand {
    private final IAccountService service;

    public PrintInfoCommand(IContainer container, IAccountService service) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException {
        switch (args.size()) {
            case 0 -> service.printInfo();
            case 1 -> service.printInfo(args.get(0));
            default -> throw new ExecutionException(Errors.IllegalArgumentCount);
        }
    }
}
