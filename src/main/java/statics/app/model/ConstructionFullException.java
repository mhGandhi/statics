package statics.app.model;

/**
 * thrown if Construction is full
 * @author Adrian Akipi
 */
public class ConstructionFullException extends RuntimeException {
    public ConstructionFullException(String message) {
        super(message);
    }
}
