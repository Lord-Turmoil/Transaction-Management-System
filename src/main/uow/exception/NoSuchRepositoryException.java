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
