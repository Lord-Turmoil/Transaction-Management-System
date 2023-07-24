/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: BaseCommand.java
 * Last Update: 7/24/23, 6:39 PM
 */

package tms.cmd;

import host.exec.IExecutable;
import ioc.IContainer;

import java.util.logging.Logger;

public abstract class BaseCommand implements IExecutable {
    protected Logger logger = null;
    protected IContainer container;

    public BaseCommand(IContainer container, Logger logger) {
        if (container == null) {
            throw new NullPointerException("Container must not be null");
        }
        this.container = container;
        this.logger = logger;
    }
}
