/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: BaseService.java
 * Last Update: 7/24/23, 4:56 PM
 */

package tms.service;

import uow.IUnitOfWork;

import java.util.logging.Logger;

public class BaseService implements IService {
    private IUnitOfWork unitOfWork;
    private Logger logger;

    public BaseService(IUnitOfWork unitOfWork, Logger logger) {
        this.unitOfWork = unitOfWork;
        this.logger = logger;
    }
}
