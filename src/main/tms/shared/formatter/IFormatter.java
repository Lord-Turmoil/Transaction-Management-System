/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter;

/**
 * By default, takes no action.
 */
public interface IFormatter {
	String format(Object obj);

	default String format(Object obj1, Object obj2) {
		return "";
	}

	default String format(Object obj1, Object obj2, Object obj3) {
		return "";
	}
}
