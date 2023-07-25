/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package uow;

import uow.exception.NoSuchRepositoryException;

public interface IUnitOfWork {
    <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls) throws NoSuchRepositoryException;
}
