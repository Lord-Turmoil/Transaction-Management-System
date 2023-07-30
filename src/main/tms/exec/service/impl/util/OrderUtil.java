/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

public class OrderUtil {
	private OrderUtil() {}

	public static int parseId(String value) throws NumberFormatException {
		if (!value.matches("^O-\\d+$")) {
			throw new NumberFormatException("Bad prefix");
		}
		return Integer.parseInt(value.substring(2));
	}
}
