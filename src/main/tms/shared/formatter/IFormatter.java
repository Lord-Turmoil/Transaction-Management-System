/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IFormatter.java
 * Last Update: 7/24/23, 9:21 PM
 */

package tms.shared.formatter;

import java.io.PrintStream;

/**
 * By default, takes no action.
 */
public interface IFormatter {
    void format(PrintStream printer, Object obj);

    default void format(PrintStream printer, Object obj1, Object obj2) {
    }

    default void format(PrintStream printer, Object obj1, Object obj2, Object obj3) {
    }
}
