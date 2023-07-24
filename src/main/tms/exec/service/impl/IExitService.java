/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IExitService.java
 * Last Update: 7/24/23, 7:40 PM
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import tms.exec.service.IService;

import java.util.List;


public interface IExitService extends IService {
    void exit(List<String> args) throws ExecutionException;
}
