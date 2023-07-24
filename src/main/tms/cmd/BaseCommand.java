/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: BaseCommand.java
 * Last Update: 7/24/23, 4:56 PM
 */

package tms.cmd;

import host.exec.IExecutable;

import java.util.logging.Logger;

public abstract class BaseCommand implements IExecutable {
    protected Logger logger = null;

    public BaseCommand(Logger logger) {
        this.logger = logger;
    }
}
