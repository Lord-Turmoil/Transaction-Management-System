/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package host.exec;

import java.util.List;

public interface IExecutable {
    void execute(List<String> args) throws ExecutionException;
}
