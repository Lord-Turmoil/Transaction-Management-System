/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.IExecutable;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IOrderService;
import tms.shared.Errors;

import java.util.List;

public class PurchaseCommodityCommand extends BaseCommand implements IExecutable {
	private final IOrderService service;
	public PurchaseCommodityCommand(IContainer container, IOrderService service) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		if (args.size() != 3) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		service.purchase(args.get(0), args.get(1), args.get(2));
	}
}
