/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 *    Filename: IUnitOfWork.java
 *
 * Last Update: 7/24/23, 1:05 PM
 */

package uow;

import uow.exception.NoSuchRepositoryException;

public interface IUnitOfWork {
    <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls) throws NoSuchRepositoryException;
}
