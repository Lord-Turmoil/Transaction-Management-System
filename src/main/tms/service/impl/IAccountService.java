/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IAccountService.java
 * Last Update: 7/24/23, 5:03 PM
 */

package tms.service.impl;

import host.exec.ExecutionException;

import java.util.List;

public interface IAccountService {
    void register(List<String> args) throws ExecutionException;

    void login(List<String> args) throws ExecutionException;
    void logout(List<String> args) throws ExecutionException;

    void printInfo(List<String> args) throws ExecutionException;
}
