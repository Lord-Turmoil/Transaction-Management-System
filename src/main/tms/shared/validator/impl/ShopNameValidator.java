/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:42
 * @Author  : Tony Skywalker
 * @File    : ShopNameValidator.java
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class ShopNameValidator implements IValidator {
    @Override
    public boolean check(String value) {
        return value.matches("^[a-zA-Z][a-zA-Z-_]{0,49}$");
    }
}
