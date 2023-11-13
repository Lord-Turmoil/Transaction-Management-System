/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd;

import host.exec.IExecutable;
import host.exec.IExecutableProvider;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.exec.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Hashtable;

public class CommandProvider implements IExecutableProvider {
    private final IContainer container;
    private final Dictionary<String, CommandArg> pool = new Hashtable<>();

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

    public CommandProvider register(String name, Class<? extends BaseCommand> executableClass, Class<? extends IService> serviceInterface) {
        return register(name, new CommandArg(executableClass, serviceInterface));
    }

    @Override
    public IExecutable resolve(String name) {
        var arg = pool.get(name);
        if (arg == null) {
            return null;
        }
        return resolveExecutable(arg.executableClass, arg);
    }

    private BaseService resolveService(Class<? extends IService> cls) {
        try {
            var serviceClass = (Class<?>) container.mapResolveRequired(cls);
            var ctor = serviceClass.getConstructor(IContainer.class);
            return (BaseService) ctor.newInstance(container);
        } catch (ClassCastException e) {
            throw new RuntimeException("Service type error", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Service constructor error", e);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate " + cls, e);
        }
    }

    private BaseCommand resolveExecutable(Class<? extends BaseCommand> cls, CommandArg arg) {
        var service = resolveService(arg.serviceInterface);
        try {
            var ctor = cls.getConstructor(IContainer.class, arg.serviceInterface);
            return ctor.newInstance(container, service);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Command constructor error");
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate " + cls);
        }
    }

    public static class CommandArg {
        public Class<? extends BaseCommand> executableClass;
        public Class<? extends IService> serviceInterface;

        public CommandArg(Class<? extends BaseCommand> executableClass, Class<? extends IService> serviceInterface) {
            this.executableClass = executableClass;
            this.serviceInterface = serviceInterface;
        }
    }
}
