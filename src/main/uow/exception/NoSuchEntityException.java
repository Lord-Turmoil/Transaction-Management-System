/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package uow.exception;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEntityException(Throwable cause) {
        super(cause);
    }
}
