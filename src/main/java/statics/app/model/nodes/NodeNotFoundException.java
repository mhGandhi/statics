package statics.app.model.nodes;

/**
 * Thrown when a Node is not found where there should be one
 * @author Adrian Akipi
 */
public class NodeNotFoundException extends RuntimeException {
    public NodeNotFoundException(String message) {
        super(message);
    }
}
