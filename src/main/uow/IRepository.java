/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package uow;

import uow.exception.NoSuchEntityException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface IRepository<TEntity> {
	IRepository<TEntity> add(TEntity entity);

	IRepository<TEntity> add(Collection<TEntity> entities);


	IRepository<TEntity> delete(TEntity entity);

	IRepository<TEntity> delete(Collection<TEntity> entities);

	IRepository<TEntity> delete(Predicate<TEntity> predicate);


	boolean exists(Predicate<TEntity> predicate);


	TEntity find(Predicate<TEntity> predicate);

	List<TEntity> findAll(Predicate<TEntity> predicate);
	List<TEntity> findAll(Predicate<TEntity> predicate, Comparator<TEntity> orderBy);

	TEntity get(Predicate<TEntity> predicate) throws NoSuchEntityException;

	List<TEntity> getAll();

	int count(Predicate<TEntity> predicate);
}
