/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IExecutableProvider.java
 * Last Update: 7/24/23, 4:56 PM
 */

package host.exec;

public interface IExecutableProvider {
    IExecutable resolve(String name);
}
