/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: UserInfoFormatter.java
 * Last Update: 7/24/23, 9:24 PM
 */

package tms.shared.formatter.impl;

import tms.model.entity.User;
import tms.shared.formatter.IFormatter;

import java.io.PrintStream;

public class UserInfoFormatter implements IFormatter {
    @Override
    public void format(PrintStream printer, Object obj) {
        User user;
        try {
            user = (User) obj;
        } catch (ClassCastException e) {
            throw new RuntimeException(obj + " is not User", e);
        }
        printer.println("Name: " + user.name);
        printer.println("Kakafee number: " + user.id);
        printer.println("Type: " + user.role);
    }
}
