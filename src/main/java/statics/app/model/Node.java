package statics.app.model;

import java.util.Objects;

/**
 * Node template for all relevant nodes
 * @author Adrian Akipi
 */
public class Node implements INode{
    /**
     * unambiguous id of the node
     */
    private final int id;

    /**
     * @param pId id of the new Node
     */
    public Node(int pId){
        this.id = pId;
    }

    /**
     * Returns id of the Node
     *
     * @return id
     */
    public int getId() {
        return this.id;
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
