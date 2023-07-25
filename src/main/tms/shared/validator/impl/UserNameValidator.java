/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class UserNameValidator implements IValidator {
	@Override
	public boolean check(String value) {
		return value.matches("^[a-zA-Z_]{4,16}$");
	}
}
