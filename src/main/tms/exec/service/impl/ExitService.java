/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;

public class ExitService extends BaseService implements IExitService {
	public ExitService(IContainer container) {
		super(container);
	}

	@Override
	public void exit() throws ExecutionException {
		printer.println("----- Good Bye! -----");
	}
}
