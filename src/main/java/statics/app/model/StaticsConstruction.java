package statics.app.model;

import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;

import java.util.List;

/**
 * just extended the default again idk
 * @author Adrian Akipi
 */
public class StaticsConstruction extends Construction {
    /**
     * create empty Construction
     */
    public StaticsConstruction() {
    }

    /**
     * create Construction with pNodes and pEdges
     *
     * @param pNodes Nodes of the Construction
     * @param pEdges Edges of the Construction
     */
    public StaticsConstruction(List<INode> pNodes, List<IEdge> pEdges) {
        super(pNodes, pEdges);
    }
}
