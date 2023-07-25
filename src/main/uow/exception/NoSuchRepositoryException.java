/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
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
