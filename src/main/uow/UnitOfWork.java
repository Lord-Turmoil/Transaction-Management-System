package uow;

import ioc.IContainer;
import ioc.NoSuchItemException;
import uow.exception.NoSuchRepositoryException;

import java.util.Dictionary;
import java.util.Hashtable;

public class UnitOfWork implements IUnitOfWork {
    private IContainer container;
    // model class
    // model repository
    private Dictionary<Class<?>, IRepository<?>> repositories = new Hashtable<>();

    public UnitOfWork(IContainer container) {
        if (container == null) {
            throw new NullPointerException("Missing container");
        }
        this.container = container;
    }

    @Override
    public <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls) throws NoSuchRepositoryException {
        var repo = repositories.get(cls);
        if (repo == null) {
            try {
                repo = container.mapResolveRequired(cls);
            } catch (NoSuchItemException e) {
                throw new NoSuchRepositoryException("Missing " + cls, e);
            }
        }
        try {
            return (IRepository<TEntity>) repo;
        } catch (ClassCastException e) {
            throw new NoSuchRepositoryException("Type mismatch", e);
        }
    }
}
