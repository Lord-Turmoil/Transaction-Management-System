/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.validator.impl;

import tms.shared.validator.IValidator;

public class IdValidator implements IValidator {
	@Override
	public boolean check(String value) {
		if (!value.matches("^\\d{12}$")) {
			return false;
		}
		try {
			var region = Integer.parseInt(value.substring(0, 4));
			if (region < 1 || region > 4500) {
				return false;
			}
			var birth = Integer.parseInt(value.substring(4, 8));
			if (birth < 1785 || birth > 1886) {
				return false;
			}
			var identity = Integer.parseInt(value.substring(8, 12));
			if (identity < 1000 || identity > 3000) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
