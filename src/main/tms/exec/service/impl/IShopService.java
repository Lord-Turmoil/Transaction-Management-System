/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

import java.util.List;

public interface IShopService extends IService {
	void register(List<String> args) throws ExecutionException;

	void list(List<String> args) throws ExecutionException;

	void cancel(List<String> args) throws ExecutionException;
}
