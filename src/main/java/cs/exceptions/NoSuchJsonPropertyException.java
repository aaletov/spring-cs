package cs.exceptions;

public class NoSuchJsonPropertyException extends IllegalArgumentException {
    public NoSuchJsonPropertyException(String message) {
        super(message);
    }
}
