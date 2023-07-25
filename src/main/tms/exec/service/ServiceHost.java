/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service;

import ioc.IContainer;
import tms.exec.service.impl.AccountService;
import tms.exec.service.impl.ExitService;
import tms.exec.service.impl.IAccountService;
import tms.exec.service.impl.IExitService;
import uow.IUnitOfWork;

import java.io.PrintStream;
import java.util.logging.Logger;

public class ServiceHost {
	private ServiceHost() {
	}

	public static void registerAll(IContainer container) {
		IUnitOfWork unitOfWork = container.resolveRequired(IUnitOfWork.class);
		PrintStream printer = container.resolveRequired(PrintStream.class);
		Logger logger = container.resolveRequired(Logger.class);

		container.addSingleton(IExitService.class, ExitService.class)
				.addSingleton(IAccountService.class, AccountService.class);
	}
}
