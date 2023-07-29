/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

public interface ICommodityService extends IService {
	void release(String shopIdString, String name, String priceString, String stockString) throws ExecutionException;

	void release(String shopIdString, String productIdString, String stockString) throws ExecutionException;

	void list() throws ExecutionException;

	void listById(String id) throws ExecutionException;    // Kakafee id

	void listByShop(String shopIdString) throws ExecutionException;

	void remove(String productIdString) throws ExecutionException;

	void remove(String productIdString, String shopIdString) throws ExecutionException;

	void search(String name) throws ExecutionException;
}
