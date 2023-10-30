/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.IExecutable;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IOrderService;
import tms.shared.Errors;
import tms.shared.validator.impl.NumberValidator;

import java.util.List;

public class ListOrderCommand extends BaseCommand implements IExecutable {
    private final IOrderService service;

    public ListOrderCommand(IContainer container, IOrderService service) {
        super(container);
        this.service = service;
    }

    @Override
    public void execute(List<String> args) throws ExecutionException {
        switch (args.size()) {
            case 0 -> service.list();
            case 1 -> {
                var id = args.get(0);
                if (new NumberValidator().check(id)) {
                    service.listById(id);
                } else {
                    service.listByShop(id);
                }
            }
            default -> throw new ExecutionException(Errors.IllegalArgumentCount);
        }
    }
}
