package statics.app.model.edges;

import statics.app.model.nodes.INode;

public class Bar extends Edge {
    /**
     * Constructor
     *
     * @param pStartNode starting Node of the relation
     * @param pEndNode   destination Node of the relation
     */
    public Bar(INode pStartNode, INode pEndNode) {
        super(pStartNode, pEndNode);
    }
}
