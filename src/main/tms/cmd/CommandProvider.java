/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: CommandProvider.java
 * Last Update: 7/24/23, 5:30 PM
 */

package tms.cmd;

import host.exec.IExecutable;
import host.exec.IExecutableProvider;
import ioc.IContainer;
import tms.service.BaseService;
import tms.service.IService;
import uow.IUnitOfWork;

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

        return resolveExecutable(arg.executableClass, resolveService(arg.serviceClass));
    }

    private BaseService resolveService(Class<? extends BaseService> cls) {
        var unitOfWork = container.resolveRequired(IUnitOfWork.class);
        var logger = container.resolve(Logger.class);
        try {
            var ctor = cls.getConstructor(IContainer.class, IUnitOfWork.class, Logger.class);
            return ctor.newInstance(container, unitOfWork, logger);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Service constructor error");
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate " + cls);
        }
    }

    private BaseCommand resolveExecutable(Class<? extends BaseCommand> cls, BaseService service) {
        var logger = container.resolve(Logger.class);
        try {
            var ctor = cls.getConstructor(IContainer.class, IService.class, Logger.class);
            return ctor.newInstance(container, service, logger);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Command constructor error");
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate " + cls);
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
