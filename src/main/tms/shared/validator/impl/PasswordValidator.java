/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: PasswordValidator.java
 * Last Update: 7/24/23, 6:52 PM
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class PasswordValidator implements IValidator {
    @Override
    public boolean check(String value) {
        return value.matches("^[a-zA-Z](?=.*\\d)(?=.*[@_%$])([^\\s]){7,15}$");
    }
}
