package cs.exceptions;

public class NotNullForeignKeyException extends IllegalArgumentException {
    public NotNullForeignKeyException(String message) {
        super(message);
    }
}
