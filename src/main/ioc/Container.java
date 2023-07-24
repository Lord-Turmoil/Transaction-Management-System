/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: Container.java
 * Last Update: 7/24/23, 4:56 PM
 */

package ioc;

import java.util.Dictionary;
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
    public <T> T resolve(Class<T> cls) {
        return mapResolve(cls);
    }

    @Override
    public <T> T mapResolve(Class<?> cls) {
        try {
            return (T) pool.get(cls);
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public <T> T resolveRequired(Class<T> cls) throws NoSuchItemException {
        return mapResolveRequired(cls);
    }

    @Override
    public <T> T mapResolveRequired(Class<?> cls) {
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
