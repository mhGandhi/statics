package statics.app.view;

/**
 * thrown, when a key-value pair is added to a map,
 * where the key is already taken and shall not be overridden
 *
 * @author Adrian Akipi
 */
public class KeyExistsException extends RuntimeException {
    public KeyExistsException(String message) {
        super(message);
    }
}
