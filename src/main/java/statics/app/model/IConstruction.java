package statics.app.model;

import statics.app.model.edges.EdgeNotFoundException;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.model.nodes.Joint;
import statics.app.model.nodes.NodeNotFoundException;

import java.util.Collection;

/**
 * Define Construction
 * @author Adrian Akipi
 */
public interface IConstruction {

    /**
     * @return Nodes of the Construction
     */
    public Collection<INode> getNodes();

    /**
     * @return Edges of the Construction
     */
    public Collection<IEdge> getEdges();

    /**
     * @return max number of Nodes in the Construction
     */
    public int getMaxNodes();

    /**
     * adds a Node to the Construction; the id will be re-chosen automatically
     * @param pNode Node to add
     * @throws NullPointerException pNode shall not be null
     * @throws ConstructionFullException thrown if the construction is full
     */
    public void addNode(INode pNode) throws NullPointerException, ConstructionFullException;

    /**
     * removes Node
     * @param pNode Node to be removed
     * @return removed Node
     * @throws NodeNotFoundException thrown if Node can not be found
     * @throws NullPointerException pNode shall not be null
     */
    public INode removeNode(INode pNode) throws NodeNotFoundException, NullPointerException;

    /**
     * removes Node
     * @param pId id of Node to be removed
     * @return removed Node
     * @throws NodeNotFoundException thrown if Node can not be found
     */
    public INode removeNode(int pId) throws NodeNotFoundException;

    /**
     * adds an Edge to the Construction; the referenced Nodes will
     * be replaced with the ones matching the ids in the argument
     * @param pEdge Edge to add
     * @param pStartId id of the starting Node
     * @param pEndId id of the ending Node
     * @throws NullPointerException pEdge shall not be null
     * @throws NodeNotFoundException thrown if the ids provided are invalid
     */
    public void addEdge(IEdge pEdge, int pStartId, int pEndId) throws NullPointerException, NodeNotFoundException;

    /**
     * removes an Edge from the Construction
     * @param pEdge Edge to remove
     * @return removed Edge
     * @throws NullPointerException pEdge shall not be null
     * @throws EdgeNotFoundException thrown if the Edge can't be removed because it can not be found
     */
    public IEdge removeEdge(IEdge pEdge) throws NullPointerException, EdgeNotFoundException;

    /**
     * removes an Edge from the Construction
     * @param pStartNode start Node of the Edge
     * @param pEndNode end Node of the Edge
     * @return removed Edge
     * @throws NullPointerException pStartNode and pEndNode shall not be null
     * @throws EdgeNotFoundException thrown if the Edge can't be removed because it can not be found
     */
    public IEdge removeEdge(INode pStartNode, INode pEndNode) throws NullPointerException, EdgeNotFoundException;

    /**
     * removes an Edge from the Construction
     * @param pStartId id of the start Node of the Edge
     * @param pEndId id of the end Node of the Edge
     * @return removed Edge
     * @throws EdgeNotFoundException thrown if the Edge can't be removed because it can not be found
     */
    public IEdge removeEdge(int pStartId, int pEndId) throws EdgeNotFoundException;

    Joint getJoint(int nodeId);

    void setJointPosition(int nodeId, SystemPos pPos);

    SystemPos validate(int nodeId, SystemPos pPos);

    double validate(int nodeId, double angle);

    void setJointAngle(int nodeId, double angle);

    double getJointAngle(int nodeId);
}