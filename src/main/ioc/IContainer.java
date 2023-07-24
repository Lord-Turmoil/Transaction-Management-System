/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IContainer.java
 * Last Update: 7/24/23, 5:30 PM
 */

package ioc;

import java.util.function.Supplier;

public interface IContainer {
    IContainer addSingleton(Class<?> cls, Object instance);

    IContainer addSingleton(Class<?> cls, Supplier<?> supplier);

    IContainer addTransient(Class<?> cls, Supplier<?> supplier);

    <T> T resolve(Class<T> cls);

    <T> T mapResolve(Class<?> cls);

    <T> T resolveRequired(Class<T> cls) throws NoSuchItemException;

    <T> T mapResolveRequired(Class<?> cls);
}
