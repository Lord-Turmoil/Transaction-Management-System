/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package host.exec;

public class ExecutionException extends Exception {
    private boolean termination = false;

    public ExecutionException() {
    }

    public ExecutionException(boolean termination) {
        this.termination = termination;
    }

    public ExecutionException(String message) {
        super(message);
    }

    public ExecutionException(Throwable cause) {
        super(cause);
    }

    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public boolean isTermination() {
        return termination;
    }
}
