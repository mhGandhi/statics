package statics.json;

/**
 * thrown, if a given value has a type that was not expected
 *
 * @author Adrian Akipi
 */
public class UnexpectedValueTypeException extends RuntimeException {
    public UnexpectedValueTypeException(String message) {
        super(message);
    }
}
