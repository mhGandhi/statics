package statics.app.model;

/**
 * Provides Guideline for how all kinds of nodes are to be implemented
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
