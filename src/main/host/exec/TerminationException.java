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
