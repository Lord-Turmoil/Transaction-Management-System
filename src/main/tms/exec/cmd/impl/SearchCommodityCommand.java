/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.IExecutable;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.ICommodityService;
import tms.shared.Errors;

import java.util.List;

public class SearchCommodityCommand extends BaseCommand implements IExecutable {
    private final ICommodityService service;

    public SearchCommodityCommand(IContainer container, ICommodityService service) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException {
        if (args.size() != 1) {
            throw new ExecutionException(Errors.IllegalArgumentCount);
        }
        service.search(args.get(0));
    }
}
