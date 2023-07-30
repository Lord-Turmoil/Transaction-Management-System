/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

public interface IOrderService extends IService {
	void purchase(String shopIdString, String productIdString, int quantity) throws ExecutionException;
	void cancel(String orderIdString) throws ExecutionException;
	void confirm(String orderIdString) throws ExecutionException;

	void listById(String id) throws ExecutionException;
	void listByShop(String shopIdString) throws ExecutionException;
}
