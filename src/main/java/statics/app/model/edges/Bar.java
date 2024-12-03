package statics.app.model.edges;

import statics.app.model.nodes.INode;
import statics.app.model.nodes.Joint;

public class Bar extends Edge {
    /**
     * Constructor
     *
     * @param pStartNode starting Node of the relation
     * @param pEndNode   destination Node of the relation
     */
    public Bar(Joint pStartNode, Joint pEndNode) {
        super(pStartNode, pEndNode);
    }

    @Override
    public Joint getStart() {
        return this.getStart();
    }

    @Override
    public Joint getEnd() {
        return this.getEnd();
    }
}
