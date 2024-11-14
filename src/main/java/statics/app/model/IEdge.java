package statics.app.model;

/**
 * Provides guideline for how all kinds of edges are to be implemented
 * has to connect 2 Nodes with each other
 * @author Adrian Akipi
 */
public interface IEdge {
    /**
     * @return Starting INode of the Edge
     */
    public INode getStart();

    /**
     * @return Destination INode of the Edge
     */
    public INode getEnd();

    /**
     * {@inheritDoc}
     * to be implemented explicitly
     */
    @Override
    public boolean equals(Object pN);
}
