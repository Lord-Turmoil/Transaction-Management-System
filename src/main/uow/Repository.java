/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package uow;

import uow.exception.NoSuchDbSetException;
import uow.exception.NoSuchEntityException;

import java.util.*;
import java.util.function.Predicate;

public class Repository<TEntity> implements IRepository<TEntity> {
	private final DbSet<TEntity> dbSet;

	public Repository(DbContext dbContext, String model) {
		if (dbContext == null) {
			throw new NullPointerException("DbContext can't be null");
		}
		try {
			this.dbSet = dbContext.getDbSet(model);
		} catch (NoSuchDbSetException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public IRepository<TEntity> add(TEntity entity) {
		dbSet.add(entity);
		return this;
	}

	@Override
	public IRepository<TEntity> add(Collection<TEntity> entities) {
		dbSet.addAll(entities);
		return this;
	}

	@Override
	public TEntity find(Predicate<TEntity> predicate) {
		for (var entity : dbSet) {
			if (predicate.test(entity)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public List<TEntity> findAll(Predicate<TEntity> predicate) {
		var entities = new ArrayList<TEntity>();
		for (var entity : dbSet) {
			if (predicate.test(entity)) {
				entities.add(entity);
			}
		}
		return entities;
	}

	@Override
	public List<TEntity> findAll(Predicate<TEntity> predicate, Comparator<TEntity> orderBy) {
		var entities = findAll(predicate);
		entities.sort(orderBy);
		return entities;
	}

	@Override
	public TEntity get(Predicate<TEntity> predicate) throws NoSuchEntityException {
		for (var entity : dbSet) {
			if (predicate.test(entity)) {
				return entity;
			}
		}
		throw new NoSuchEntityException();
	}

	@Override
	public List<TEntity> getAll() {
		return dbSet.stream().toList();
	}

	@Override
	public int count(Predicate<TEntity> predicate) {
		int cnt = 0;
		for (var entity : dbSet) {
			if (predicate.test(entity)) {
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * Here, entity must come from dbSet! Since address comparison
	 * is used.
	 */
	@Override
	public IRepository<TEntity> delete(TEntity entity) {
		dbSet.remove(entity);
		return this;
	}

	@Override
	public IRepository<TEntity> delete(Collection<TEntity> entities) {
		dbSet.removeAll(entities);
		return this;
	}

	@Override
	public IRepository<TEntity> delete(Predicate<TEntity> predicate) {
		var dirty = new ArrayList<TEntity>();
		for (var entity : dbSet) {
			if (predicate.test(entity)) {
				dirty.add(entity);
			}
		}
		dbSet.removeAll(dirty);
		return this;
	}

	@Override
	public boolean exists(Predicate<TEntity> predicate) {
		return dbSet.stream().anyMatch(predicate);
	}
}
