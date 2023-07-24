/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IDumbService.java
 * Last Update: 7/24/23, 7:25 PM
 */

package tms.service.impl;

import host.exec.ExecutionException;
import tms.service.IService;

import java.util.List;


public interface IExitService extends IService {
    void exit(List<String> args) throws ExecutionException;
}
