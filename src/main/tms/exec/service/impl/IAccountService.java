/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

public interface IAccountService extends IService {
    void register(String id, String name, String password, String confirm, String role) throws ExecutionException;

    void login(String id, String password) throws ExecutionException;

    void logout() throws ExecutionException;

    void printInfo(String id) throws ExecutionException;

    void printInfo() throws ExecutionException;
}
