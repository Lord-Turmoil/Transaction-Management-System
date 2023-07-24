/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: DefaultCommandParser.java
 * Last Update: 7/24/23, 5:30 PM
 */

package host.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DefaultCommandParser implements ICommandParser {
    @Override
    public List<String> Parse(String line) {
        ArrayList<String> args = new ArrayList<>();
        try (Scanner scanner = new Scanner(line)) {
            while (scanner.hasNext()) {
                args.add(scanner.next());
            }
        }
        return args;
    }
}
