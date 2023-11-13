/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter.impl;

import tms.shared.formatter.IFormatter;
import tms.shared.formatter.IPrintHandler;

import java.io.PrintStream;
import java.util.Collection;

public class PrintHandler implements IPrintHandler {
    private final PrintStream printer;

    public PrintHandler(PrintStream printer) {

        this.printer = printer;
    }

    @Override
    public <T> void print(T object, IFormatter formatter) {
        this.printer.println(formatter.format(object));
    }

    @Override
    public <T> void print(Collection<T> objects, IFormatter formatter) {
        for (var obj : objects) {
            print(obj, formatter);
        }
    }
}
