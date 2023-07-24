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
