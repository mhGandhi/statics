package statics.json;

/**
 * thrown, if a given object or primitive can not be converted to json because there is no conversion implementation
 *
 * @author Adrian Akipi
 */
public class UnsupportedValueTypeException extends RuntimeException {
    public UnsupportedValueTypeException(String message) {
        super(message);
    }
}
