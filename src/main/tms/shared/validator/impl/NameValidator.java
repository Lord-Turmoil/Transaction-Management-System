/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: NameValidator.java
 * Last Update: 7/24/23, 6:39 PM
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class NameValidator implements IValidator {
    @Override
    public boolean check(String value) {
        return value.matches("^[a-zA-Z][a-zA-Z_]{3,15}$");
    }
}
