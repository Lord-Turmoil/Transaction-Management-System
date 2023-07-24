/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: TerminationException.java
 * Last Update: 7/24/23, 4:56 PM
 */

package host.exec;

public class TerminationException extends Exception {
    public TerminationException() {
    }

    public TerminationException(String message) {
        super(message);
    }

    public TerminationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminationException(Throwable cause) {
        super(cause);
    }
}
