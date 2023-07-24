/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IExecutable.java
 * Last Update: 7/24/23, 4:56 PM
 */

package host.exec;

import java.util.List;

public interface IExecutable {
    void execute(List<String> args) throws ExecutionException, TerminationException;
}
