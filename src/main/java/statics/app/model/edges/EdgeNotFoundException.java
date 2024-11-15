package statics.app.model.edges;

/**
 * Thrown when an Edge is not found where there should be one
 * @author Adrian Akipi
 */
public class EdgeNotFoundException extends RuntimeException {
    public EdgeNotFoundException(String message){super(message);}
}
