/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class NumberValidator implements IValidator {
	@Override
	public boolean check(String value) {
		return value.matches("^\\d+$");
	}
}
