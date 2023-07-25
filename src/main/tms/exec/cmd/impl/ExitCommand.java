/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.IExitService;

import java.util.List;

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
