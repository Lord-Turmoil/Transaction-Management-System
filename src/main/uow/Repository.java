/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: Repository.java
 * Last Update: 7/24/23, 4:57 PM
 */

package uow;

import uow.exception.NoSuchDbSetException;
import uow.exception.NoSuchEntityException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class Repository<TEntity> implements IRepository<TEntity> {
    private DbSet<TEntity> dbSet;
    private Predicate<TEntity> predicate;

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
    public TEntity find(Predicate<TEntity> predicate) {
        for (var entity : dbSet) {
            if (predicate.test(entity)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Collection<TEntity> findAll() {
        return dbSet.stream().toList();
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
    public IRepository<TEntity> delete(Predicate<TEntity> predicate) {
        var dirty = new ArrayList<>();
        for (var entity : dbSet) {
            if (predicate.test(entity)) {
                dirty.add(entity);
            }
        }
        dbSet.removeAll(dirty);
        return this;
    }
}
