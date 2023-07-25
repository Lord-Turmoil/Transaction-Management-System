/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.shared.Errors;

import java.util.List;

public class ExitService extends BaseService implements IExitService {
    public ExitService(IContainer container) {
        super(container);
    }

    @Override
    public void exit(List<String> args) throws ExecutionException {
        if (args.size() > 0) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        printer.println("----- Good Bye! -----");
    }
}
