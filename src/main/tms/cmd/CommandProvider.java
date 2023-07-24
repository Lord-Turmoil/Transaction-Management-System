/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 *    Filename: CommandProvider.java
 *
 * Last Update: 7/24/23, 4:55 PM
 */

package tms.cmd;

import host.exec.IExecutable;
import host.exec.IExecutableProvider;
import ioc.IContainer;
import tms.service.BaseService;
import tms.service.IService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Logger;

public class CommandProvider implements IExecutableProvider {
    private IContainer container;
    private Dictionary<String, CommandArg> pool = new Hashtable<>();
    public CommandProvider(IContainer container) {
        if (container == null) {
            throw new NullPointerException("Missing container");
        }
        this.container = container;
    }

    public CommandProvider register(String name, CommandArg arg) {
        pool.put(name, arg);
        return this;
    }

    public CommandProvider register(String name, Class<? extends BaseCommand> executableClass, Class<? extends BaseService> serviceClass) {
        return register(name, new CommandArg(executableClass, serviceClass));
    }

    @Override
    public IExecutable resolve(String name) {
        var arg = pool.get(name);
        if (arg == null) {
            return null;
        }

        var service = container.resolveRequired(arg.serviceClass);
        var logger = container.resolve(Logger.class);

        Constructor<? extends BaseCommand> cmdCtor = null;
        try {
            cmdCtor = arg.executableClass.getConstructor(IService.class, Logger.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Command constructor error");
        }

        try {
            return cmdCtor.newInstance(service, logger);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate " + name);
        }
    }

    static class CommandArg {
        public Class<? extends BaseCommand> executableClass;
        public Class<? extends BaseService> serviceClass;

        public CommandArg(Class<? extends BaseCommand> executableClass, Class<? extends BaseService> serviceClass) {
            this.executableClass = executableClass;
            this.serviceClass = serviceClass;
        }
    }
}
