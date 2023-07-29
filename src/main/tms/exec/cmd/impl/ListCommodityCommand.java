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
import tms.shared.Errors;
import tms.shared.validator.impl.NumberValidator;

import java.util.List;

public class ListCommodityCommand extends BaseCommand implements IExecutable {
	private final ICommodityService service;

	public ListCommodityCommand(IContainer container, ICommodityService service) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		if (args.isEmpty()) {
			service.list();
		} else if (args.size() == 1) {
			String value = args.get(0);
			if (new NumberValidator().check(value)) {
				service.listById(value);
			} else {
				service.listByShop(value);
			}
		} else {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
	}
}
