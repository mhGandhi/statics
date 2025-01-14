package statics.app.model;

import statics.app.DegreesOfFreedom;
import statics.app.model.edges.Edge;
import statics.app.model.edges.EdgeNotFoundException;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.model.nodes.Joint;
import statics.app.model.nodes.Node;
import statics.app.model.nodes.NodeNotFoundException;
import statics.app.view.ScaleOutOfBoundsException;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
//todo tests mby??

/**
 * default Construction
 * @author Adrian Akipi
 */
public class Construction implements IConstruction{
    /**
     * Nodes of the Construction
     */
    private List<INode> nodes;

    /**
     * Edges of the Construction
     */
    private List<IEdge> edges;

    /**
     * up to what number ids are taken
     */
    private int idIncrement;

    /**
     * Collection of all free Ids below idIncrement
     */
    private List<Integer> freeIdsBelowIncrement;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return Nodes of the Construction
     */
    @Override
    public Collection<INode> getNodes() {
        return this.nodes;
    }

    /**
     * @return Edges of the Construction
     */
    @Override
    public Collection<IEdge> getEdges() {
        return this.edges;
    }

    /**
     * @return max number of Nodes in the Construction
     */
    @Override
    public int getMaxNodes(){
        return 10_000;//todo link setting
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * create empty Construction
     */
    public Construction(){
        this(null,null);
    }

    /**
     * create Construction with pNodes and pEdges
     *
     * @param pNodes Nodes of the Construction
     * @param pEdges Edges of the Construction
     */
    public Construction(List<INode> pNodes, List<IEdge> pEdges){
        if(pNodes==null)pNodes = new LinkedList<>();
        if(pEdges==null)pEdges = new LinkedList<>();

        this.nodes = pNodes;
        this.edges = pEdges;

        this.idIncrement = 0;
        this.freeIdsBelowIncrement = List.of();
        //todo fix id assignment for existing graphs
        for(INode n : this.nodes){
            n=n.withId(pullNextFreeId());
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * gets free id and marks it as taken
     * @return the free id
     */
    private int pullNextFreeId(){
        int ret;
        if(this.freeIdsBelowIncrement.isEmpty()){
            ret = this.idIncrement;
            this.idIncrement++;
        }else{
            ret = freeIdsBelowIncrement.removeFirst();
        }
        return ret;
    }
    /**
     * adds a Node to the Construction; the id will be re-chosen automatically
     *
     * @param pNode Node to add
     * @throws NullPointerException pNode shall not be null
     * @throws ConstructionFullException thrown if the construction is full
     */
    @Override
    public void addNode(INode pNode) throws NullPointerException, ConstructionFullException {
        if(pNode == null)throw new NullPointerException();
        if(this.nodes.size() >= getMaxNodes())
            throw new ConstructionFullException(
                    "adding this Node would exceed the limit of " +getMaxNodes()+" of this Construction"
            );

        pNode = pNode.withId(pullNextFreeId());

        this.nodes.add(pNode);
    }

    /**
     * removes Node
     *
     * @param pNode Node to be removed
     * @return removed Node
     * @throws NodeNotFoundException thrown if Node can not be found
     */
    @Override
    public INode removeNode(INode pNode) throws NodeNotFoundException, NullPointerException{
        if(pNode == null)throw new NullPointerException();
        INode ret = null;
        for(INode n : this.nodes){
            if(n.equals(pNode)){
                ret = n;
                this.nodes.remove(n);
                break;
            }
        }
        if(ret == null)throw new NodeNotFoundException("Node with id "+pNode.getId()+" not found in Construction");
        this.freeIdsBelowIncrement.add(ret.getId());
        removeAllEdgesConnectedTo(ret);
        return ret;
    }

    /**
     * removes Node
     *
     * @param pId id of Node to be removed
     * @return removed Node
     * @throws NodeNotFoundException thrown if Node can not be found
     */
    @Override
    public INode removeNode(int pId) throws NodeNotFoundException {
        return removeNode(new Node(pId));
    }

    /**
     * adds an Edge to the Construction; the referenced Nodes will
     * be replaced with the ones matching the ids in the argument
     *
     * @param pEdge    Edge to add
     * @param pStartId id of the starting Node
     * @param pEndId   id of the ending Node
     * @throws NullPointerException  pEdge shall not be null
     * @throws NodeNotFoundException thrown if the ids provided are invalid
     */
    @Override
    public void addEdge(IEdge pEdge, int pStartId, int pEndId) throws NullPointerException, NodeNotFoundException {
        if(pEdge == null)throw new NullPointerException();
        INode n1 = null;
        INode n2 = null;
        for (INode n : this.nodes){
            if(n.getId()==pStartId)n1 = n;
            if(n.getId()==pEndId)n2 = n;
            if(n1 != null && n2 != null)break;
        }
        if(n1==null||n2==null)throw new NodeNotFoundException(
                "Edge cannot be created if end Nodes don't exist in Construction"
        );
        this.edges.add(pEdge.from(n1).to(n2));
    }

    /**
     * removes an Edge from the Construction
     *
     * @param pEdge Edge to remove
     * @return removed Edge
     * @throws NullPointerException  pEdge shall not be null
     * @throws EdgeNotFoundException thrown if the Edge can't be removed because it can not be found
     */
    @Override
    public IEdge removeEdge(IEdge pEdge) throws NullPointerException, EdgeNotFoundException {
        if(pEdge == null)throw new NullPointerException();
        IEdge ret = null;
        for(IEdge e : this.edges){
            if(e.equals(pEdge)){
                ret = e;
                this.edges.remove(e);
                break;
            }
        }
        if(ret == null)throw new EdgeNotFoundException(
                "No Edge between Node "+pEdge.getStart()+" and Node " +pEdge.getEnd()+" found in Construction"
        );
        return ret;
    }

    /**
     * removes an Edge from the Construction
     *
     * @param pStartNode start Node of the Edge
     * @param pEndNode   end Node of the Edge
     * @return removed Edge
     * @throws NullPointerException  pStartNode and pEndNode shall not be null
     * @throws EdgeNotFoundException thrown if the Edge can't be removed because it can not be found
     */
    @Override
    public IEdge removeEdge(INode pStartNode, INode pEndNode) throws NullPointerException, EdgeNotFoundException {
        if(pStartNode == null || pEndNode == null)throw new NullPointerException();
        return removeEdge(new Edge(pStartNode,pEndNode));
    }

    /**
     * removes an Edge from the Construction
     *
     * @param pStartId id of the start Node of the Edge
     * @param pEndId   id of the end Node of the Edge
     * @return removed Edge
     * @throws EdgeNotFoundException thrown if the Edge can't be removed because it can not be found
     */
    @Override
    public IEdge removeEdge(int pStartId, int pEndId) throws EdgeNotFoundException {
        return removeEdge(new Node(pStartId), new Node(pEndId));
    }

    @Override
    public Joint getJoint(int nodeId) {
        for(INode node : getNodes()){
            if(node instanceof Joint j && node.getId()==nodeId){
                return j;
            }
        }
        return null;
    }

    @Override
    public void setJointPosition(int nodeId, SystemPos pPos) {
        for(INode node : getNodes()){
            if(node instanceof Joint j && node.getId()==nodeId){
                j.setPos(pPos);
                return;
            }
        }
        //todo throw
    }

    @Override
    public SystemPos validate(int nodeId, SystemPos pPos) {
        Joint nd = getJoint(nodeId);


        double cosA = Math.cos(Math.toRadians(nd.getAngle()));
        double sinA = Math.sin(Math.toRadians(nd.getAngle()));

        double cosAi = Math.cos(Math.toRadians(-nd.getAngle()));
        double sinAi = Math.sin(Math.toRadians(-nd.getAngle()));

        double[][] rotMat = {
                {cosA, -sinA},
                {sinA, cosA}
        };
        double[][] invRotMat = {
                {cosAi, -sinAi},
                {sinAi, cosAi}
        };

        double[] plannedOffset = {nd.getPos().getX()-pPos.getX(), nd.getPos().getY()-pPos.getY()};
        double[] relativeOffset = {
                plannedOffset[0]*rotMat[0][0]+plannedOffset[1]*rotMat[0][1],
                plannedOffset[0]*rotMat[1][0]+plannedOffset[1]*rotMat[1][1]
        };
        //double[] relativeOffset = {plannedOffset[0]/cosA, plannedOffset[1]/cosA};


        if(nd.isSupport()){
            if(!nd.getDegreesOfFreedom().contains(DegreesOfFreedom.X)){
                relativeOffset[0] = 0;
            }
            if(!nd.getDegreesOfFreedom().contains(DegreesOfFreedom.Y)){
                relativeOffset[1] = 0;
            }
        }


        //double[] offset = {relativeOffset[0]*cosA, relativeOffset[1]*cosA};
        double[] offset = {
                relativeOffset[0]*invRotMat[0][0]+relativeOffset[1]*invRotMat[0][1],
                relativeOffset[0]*invRotMat[1][0]+relativeOffset[1]*invRotMat[1][1]
        };

        SystemPos ret = new SystemPos(nd.getPos());
        ret.setX(ret.getX()-offset[0]);
        ret.setY(ret.getY()-offset[1]);

        //System.out.println("---------------------------------");
        //System.out.println(Arrays.toString(plannedOffset));
        //System.out.println(Arrays.toString(relativeOffset));
        //System.out.println(Arrays.toString(offset));
        //System.out.println(pPos.toString());
        //System.out.println(ret.toString());

        return ret;
    }

    /**
     * removes all Edges connected to INode
     * @param pNode node that is to be cleared of Edges
     */
    public void removeAllEdgesConnectedTo(INode pNode) {
        this.edges.removeIf(e -> e.getStart() == pNode || e.getEnd() == pNode);
    }
}
