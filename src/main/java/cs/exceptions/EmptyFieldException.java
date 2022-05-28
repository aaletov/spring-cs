package cs.exceptions;

public class EmptyFieldException extends IllegalArgumentException {
    public EmptyFieldException(String message) {
        super(message);
    }
}
