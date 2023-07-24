package ioc;

public class NoSuchItemException extends Exception {
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
