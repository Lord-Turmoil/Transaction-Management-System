/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: LogoutCommand.java
 * Last Update: 7/24/23, 9:16 PM
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IAccountService;

import java.util.List;

public class LogoutCommand extends BaseCommand {
    private final IAccountService service;

    public LogoutCommand(IAccountService service, IContainer container) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException, TerminationException {
        service.logout(args);
    }
}