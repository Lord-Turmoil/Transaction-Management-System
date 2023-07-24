package uow.exception;

public class NoSuchDbSetException extends RuntimeException {
    public NoSuchDbSetException() {
    }

    public NoSuchDbSetException(String message) {
        super(message);
    }

    public NoSuchDbSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDbSetException(Throwable cause) {
        super(cause);
    }
}
