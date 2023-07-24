package uow;

import uow.exception.NoSuchRepositoryException;

public interface IUnitOfWork {
    <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls) throws NoSuchRepositoryException;
}
