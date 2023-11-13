/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package host.parser;

import java.util.List;

public interface ICommandParser {
    List<String> Parse(String line);
}
