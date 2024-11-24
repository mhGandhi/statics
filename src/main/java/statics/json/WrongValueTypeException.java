package statics.json;

/**
 * thrown, if a given value has a type that was not expected
 *
 * @author Adrian Akipi
 */
public class WrongValueTypeException extends RuntimeException {
    public WrongValueTypeException(String message) {
        super(message);
    }
}
