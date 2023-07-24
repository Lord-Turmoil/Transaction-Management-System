/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: NoSuchItemException.java
 * Last Update: 7/24/23, 5:30 PM
 */

package ioc;

public class NoSuchItemException extends RuntimeException {
    public NoSuchItemException() {
    }

    public NoSuchItemException(String message) {
        super(message);
    }

    public NoSuchItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchItemException(Throwable cause) {
        super(cause);
    }
}
