package statics.app.model.edges;

import statics.app.model.nodes.INode;

import java.util.Objects;

/**
 * pattern for Edges
 * @author Adrian Akipi
 */
public class Edge implements IEdge {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * starting Node of the relation
     */
    protected INode start;

    /**
     * destination Node of the relation
     */
    protected INode end;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return starting INode of the Edge
     */
    @Override
    public INode getStart() {
        return this.start;
    }

    /**
     * @return destination INode of the Edge
     */
    @Override
    public INode getEnd() {
        return this.end;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Constructor
     * @param pStartNode starting Node of the relation
     * @param pEndNode destination Node of the relation
     */
    public Edge(INode pStartNode, INode pEndNode){
        this.start = pStartNode;
        this.end = pEndNode;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * returns the Edge with different start Node
     *
     * @param pN new start Node
     * @return Edge
     */
    @Override
    public IEdge from(INode pN) {
        this.start = pN;
        return this;
    }

    /**
     * returns the Edge with different end Node
     *
     * @param pN new end Node
     * @return Edge
     */
    @Override
    public IEdge to(INode pN) {
        this.end = pN;
        return this;
    }

    /**
     * {@inheritDoc}
     * start and end are exchangeable
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(start, edge.end) && Objects.equals(end, edge.start)
                || Objects.equals(start, edge.start) && Objects.equals(end, edge.end);
    }
}
