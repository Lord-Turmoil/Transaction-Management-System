/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package host.exec;

public interface IExecutableProvider {
	IExecutable resolve(String name);
}
