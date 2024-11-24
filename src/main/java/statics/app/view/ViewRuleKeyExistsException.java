package statics.app.view;

/**
 * thrown, when a key-value pair is added to a view rule map,
 * where the key is already taken and shall not be overridden
 *
 * @author Adrian Akipi
 */
public class ViewRuleKeyExistsException extends RuntimeException {
    public ViewRuleKeyExistsException(String message) {
        super(message);
    }
}
