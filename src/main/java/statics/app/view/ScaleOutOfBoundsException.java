package statics.app.view;

/**
 * thrown if a given scale is out of bounds
 *
 * @author Adrian Akipi
 */
public class ScaleOutOfBoundsException extends RuntimeException {
    public ScaleOutOfBoundsException(String message) {
        super(message);
    }
}
