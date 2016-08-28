package nz.colin.mtef.exceptions;

/**
 * Created by colin on 26/08/16.
 */
public class ParseException extends Exception {

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}