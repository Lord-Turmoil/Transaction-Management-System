/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: ICommandParser.java
 * Last Update: 7/24/23, 4:56 PM
 */

package host.parser;

import java.util.List;

public interface ICommandParser {
    List<String> Parse(String line);
}
