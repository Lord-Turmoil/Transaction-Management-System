/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.IExecutable;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.ICommodityService;
import tms.model.entity.Product;
import tms.model.entity.Shop;
import tms.shared.Errors;
import tms.shared.validator.impl.PriceValidator;

import java.math.BigDecimal;
import java.util.List;

public class ReleaseCommodityCommand extends BaseCommand implements IExecutable {
	private final ICommodityService service;

	public ReleaseCommodityCommand(IContainer container, ICommodityService service) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		switch (args.size()) {
			case 3 -> service.release(args.get(0), args.get(1), args.get(2));
			case 4 -> service.release(args.get(0), args.get(1), args.get(2), args.get(3));
			default -> throw new ExecutionException(Errors.IllegalArgumentCount);
		}
	}
}
