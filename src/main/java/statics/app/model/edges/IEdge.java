package statics.app.model.edges;

import statics.app.model.nodes.INode;

/**
 * Provides guideline for how all kinds of Edges are to be implemented
 * has to connect 2 Nodes with each other.
 * @author Adrian Akipi
 */
public interface IEdge {
    /**
     * @return starting INode of the Edge
     */
    public INode getStart();

    /**
     * @return destination INode of the Edge
     */
    public INode getEnd();

    /**
     * returns the Edge with different start Node
     * @param pN new start Node
     * @return Edge
     */
    public IEdge from(INode pN);//todo use copy

    /**
     * returns the Edge with different end Node
     * @param pN new end Node
     * @return Edge
     */
    public IEdge to(INode pN);//todo use copy

    /**
     * {@inheritDoc}
     * to be implemented explicitly
     */
    @Override
    public boolean equals(Object pN);
}
