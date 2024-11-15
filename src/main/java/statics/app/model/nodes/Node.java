package statics.app.model.nodes;

import java.util.Objects;

/**
 * Node template for all relevant Nodes
 * @author Adrian Akipi
 */
public class Node implements INode {
    /**
     * unambiguous id of the Node
     */
    private int id;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * returns id of the Node
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param pId id of the new Node
     */
    public Node(int pId){
        this.id = pId;
    }

    /**
     * creates Node with id -1
     */
    public Node(){
        this(-1);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * returns the Node with different id
     *
     * @param pId new id
     * @return Node
     */
    @Override
    public INode withId(int pId) {
        this.id = pId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof INode nd){
            return getId() == nd.getId();
        }else{
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }
}
