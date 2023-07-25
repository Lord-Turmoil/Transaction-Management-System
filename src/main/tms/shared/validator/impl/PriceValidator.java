/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class PriceValidator implements IValidator {
    @Override
    public boolean check(String value) {
        return value.matches("^(([1-9]\\d{0,7})|0)(\\.\\d{1,2})?$");
    }
}
