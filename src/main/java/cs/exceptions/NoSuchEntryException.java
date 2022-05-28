package cs.exceptions;

import java.sql.SQLException;

public class NoSuchEntryException extends SQLException {
    public NoSuchEntryException(String message) {
        super(message);
    }
}
