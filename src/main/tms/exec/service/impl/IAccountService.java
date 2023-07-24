/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IAccountService.java
 * Last Update: 7/24/23, 7:26 PM
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

import java.util.List;

public interface IAccountService extends IService {
    void register(List<String> args) throws ExecutionException;

    void login(List<String> args) throws ExecutionException;

    void logout(List<String> args) throws ExecutionException;

    void printInfo(List<String> args) throws ExecutionException;
}
