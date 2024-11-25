package statics.json;

/**
 * thrown, if provided data does not meet an expected syntax
 *
 * @author Adrian Akipi
 */
public class InvalidSyntaxException extends RuntimeException {
    public InvalidSyntaxException(String message) {
        super(message);
    }
}
