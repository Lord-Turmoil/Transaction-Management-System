/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

import java.math.BigDecimal;

public interface ICommodityService extends IService {
	void release(int shopId, String name, BigDecimal price, int stock) throws ExecutionException;

	void release(int shopId, int productId, int stock) throws ExecutionException;

	void list() throws ExecutionException;
	void list(String id) throws ExecutionException;    // Kakafee id

	void list(int shopId) throws ExecutionException;

	void search(String name) throws ExecutionException;
}
