/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: RegisterCommand.java
 * Last Update: 7/24/23, 7:20 PM
 */

package tms.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.cmd.BaseCommand;
import tms.service.impl.IAccountService;

import java.util.List;

public class RegisterCommand extends BaseCommand {
    private final IAccountService service;

    public RegisterCommand(IAccountService service, IContainer container) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException, TerminationException {
        service.register(args);
    }
}
