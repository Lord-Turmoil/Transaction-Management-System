package ioc;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Container implements IContainer {
    private static Container globalContainer = null;

    private Dictionary<Class<?>, Object> pool = new Hashtable<>();

    public static Container getGlobal() {
        if (globalContainer == null) {
            globalContainer = new Container();
        }
        return globalContainer;
    }

    /**
     * Will overwrite old value.
     */
    @Override
    public IContainer register(Class<?> cls, Object instance) {
        pool.put(cls, instance);
        return this;
    }

    @Override
    public <T> T resolve(Class<?> cls) {
        try {
            return (T) pool.get(cls);
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public <T> T resolveRequired(Class<?> cls) throws NoSuchItemException {
        try {
            var instance = (T) pool.get(cls);
            if (instance == null) {
                throw new NoSuchItemException();
            }
            return instance;
        } catch (ClassCastException e) {
            throw new NoSuchItemException("Type mismatch", e);
        } catch (NoSuchItemException e) {
            throw e;
        }
    }
}
