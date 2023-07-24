/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: DumbService.java
 * Last Update: 7/24/23, 7:26 PM
 */

package tms.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.service.BaseService;
import tms.shared.Errors;
import uow.IUnitOfWork;

import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

public class ExitService extends BaseService implements IExitService {
    public ExitService(IContainer container, IUnitOfWork unitOfWork, PrintStream printer, Logger logger) {
        super(container, unitOfWork, printer, logger);
    }

    @Override
    public void exit(List<String> args) throws ExecutionException {
        if (args.size() > 0) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        printer.println("----- Good Bye! -----");
    }
}
