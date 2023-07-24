/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: NameValidator.java
 * Last Update: 7/24/23, 5:30 PM
 */

package tms.validator.impl;

import tms.validator.IValidator;

public class NameValidator implements IValidator {
    @Override
    public boolean check(String value) {
        return value.matches("^[a-zA-Z][a-zA-Z_]{3,15}$");
    }
}
