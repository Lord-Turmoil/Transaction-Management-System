/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: BaseCommand.java
 * Last Update: 7/24/23, 8:50 PM
 */

package tms.exec.cmd;

import host.exec.IExecutable;
import ioc.IContainer;

import java.util.logging.Logger;

public abstract class BaseCommand implements IExecutable {
    protected Logger logger;
    protected IContainer container;

    public BaseCommand(IContainer container) {
        if (container == null) {
            throw new NullPointerException("Container must not be null");
        }
        this.container = container;
        this.logger = container.resolve(Logger.class);
    }
}