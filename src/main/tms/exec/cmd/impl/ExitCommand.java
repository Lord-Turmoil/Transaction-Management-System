/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IExitService;
import tms.shared.Errors;

import java.util.List;

public class ExitCommand extends BaseCommand {
    private final IExitService service;

    public ExitCommand(IContainer container, IExitService service) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException {
        if (!args.isEmpty()) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        service.exit();
        throw new ExecutionException(true);
    }
}
