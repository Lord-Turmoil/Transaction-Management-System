/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IRepository.java
 * Last Update: 7/24/23, 4:56 PM
 */

package uow;

import uow.exception.NoSuchEntityException;

import java.util.Collection;
import java.util.function.Predicate;

public interface IRepository<TEntity> {
    IRepository<TEntity> add(TEntity entity);

    TEntity find(Predicate<TEntity> predicate);

    Collection<TEntity> findAll();

    TEntity get(Predicate<TEntity> predicate) throws NoSuchEntityException;

    IRepository<TEntity> delete(TEntity entity);

    IRepository<TEntity> delete(Predicate<TEntity> predicate);
}
