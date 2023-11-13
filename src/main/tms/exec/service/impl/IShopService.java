/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

public interface IShopService extends IService {
    void register(String name) throws ExecutionException;

    void list() throws ExecutionException;

    void list(String id) throws ExecutionException;

    void cancel(String shopIdString) throws ExecutionException;
}
