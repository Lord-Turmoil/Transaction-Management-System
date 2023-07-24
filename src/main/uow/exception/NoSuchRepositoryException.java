/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: NoSuchRepositoryException.java
 * Last Update: 7/24/23, 4:56 PM
 */

package uow.exception;

public class NoSuchRepositoryException extends RuntimeException {
    public NoSuchRepositoryException() {
    }

    public NoSuchRepositoryException(String message) {
        super(message);
    }

    public NoSuchRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRepositoryException(Throwable cause) {
        super(cause);
    }
}
