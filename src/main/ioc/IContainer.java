/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IContainer.java
 * Last Update: 7/24/23, 4:56 PM
 */

package ioc;

public interface IContainer {
    IContainer register(Class<?> cls, Object instance);

    <T> T resolve(Class<T> cls);

    <T> T mapResolve(Class<?> cls);

    <T> T resolveRequired(Class<T> cls) throws NoSuchItemException;

    <T> T mapResolveRequired(Class<?> cls);
}
