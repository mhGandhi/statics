package statics.app.model.nodes;

/**
 * Provides Guideline for how all kinds of Nodes are to be implemented
 * @author Adrian Akipi
 */
public interface INode {

    /**
     * Returns id of the Node
     *
     * @return id
     */
    public int getId();

    /**
     * returns the Node with different id
     * @param pId new id
     * @return Node
     */
    public INode withId(int pId);//todo use copy

    /**
     * {@inheritDoc}
     * to be implemented explicitly
     */
    @Override
    public boolean equals(Object pN);

    /**
     * {@inheritDoc}
     * to be implemented explicitly
     */
    @Override
    public int hashCode();
}
