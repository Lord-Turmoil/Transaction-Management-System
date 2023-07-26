/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter.impl;

import tms.model.entity.User;
import tms.shared.formatter.IFormatter;

public class UserInfoFormatter implements IFormatter {
	@Override
	public String format(Object obj) {
		User user;
		try {
			user = (User) obj;
		} catch (ClassCastException e) {
			throw new RuntimeException(obj + " is not User", e);
		}
		return "Name: " + user.name + '\n' +
				"Kakafee number: " + user.id + '\n' +
				"Type: " + user.role;
	}
}
