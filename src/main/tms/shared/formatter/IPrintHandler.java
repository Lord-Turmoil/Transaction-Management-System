/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter;

import java.util.Collection;

public interface IPrintHandler {
	void print(Object object, IFormatter formatter);
	void print(Collection<Object> objects, IFormatter formatter);
	default void print(Object obj1, Object obj2, IFormatter formatter) {}
	default void print(Object obj1, Object obj2, Object obj3, IFormatter formatter) {}
}
