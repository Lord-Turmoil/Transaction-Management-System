/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 *    Filename: DbContext.java
 *
 * Last Update: 7/24/23, 1:05 PM
 */

package uow;

import uow.exception.NoSuchDbSetException;

public class DbContext {
    public <T> DbSet<T> getDbSet(String name) throws NoSuchDbSetException {
        try {
            var field = this.getClass().getField(name);
            var dbSet = (DbSet<T>) field.get(this);
            if (dbSet == null) {
                dbSet = new DbSet<T>();
            }
            return dbSet;
        } catch (Exception e) {
            throw new NoSuchDbSetException(e.getMessage(), e);
        }
    }
}
