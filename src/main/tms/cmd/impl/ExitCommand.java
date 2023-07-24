/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: ExitCommand.java
 * Last Update: 7/24/23, 7:24 PM
 */

package tms.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.cmd.BaseCommand;
import tms.service.impl.IExitService;

import java.util.List;
import java.util.logging.Logger;

public class ExitCommand extends BaseCommand {
    private final IExitService service;

    public ExitCommand(IExitService service, IContainer container) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException, TerminationException {
        service.exit(args);
        throw new TerminationException();
    }
}
