package statics.app.model.nodes;

import statics.app.DegreesOfFreedom;
import statics.app.model.SystemPos;

import java.util.Collection;

/**
 * portrays a joint in a construction
 * @author Adrian Akipi
 */
public class Joint extends Node {
    /**
     * position within system
     */
    private SystemPos pos;

    /**
     * value of the joint
     */
    private Collection<DegreesOfFreedom> degreesOfFreedom;

    /**
     * whether the joint is fixed to the environment
     */
    private boolean support;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return position of the Joint
     */
    public SystemPos getPos(){
        return this.pos;
    }

    /**
     * @param pPos new position for the Joint
     */
    public void setPos(SystemPos pPos){
        this.pos = pPos;
    }

    /**
     * @return dof of the joint
     */
    public Collection<DegreesOfFreedom> getDegreesOfFreedom(){
        return this.degreesOfFreedom;
    }

    /**
     * @return whether the joint is a support
     */
    public boolean isSupport() {
        return support;
    }

    /**
     * @param degreesOfFreedom Collection of dof that define the joint
     */
    public void setDegreesOfFreedom(Collection<DegreesOfFreedom> degreesOfFreedom) {
        this.degreesOfFreedom = degreesOfFreedom;
    }

    /**
     * @param support sets whether the joint is a support
     */
    public void setSupport(boolean support) {
        this.support = support;
    }

    /**
     * returns the number of reactions originating from this joint
     * @return value of the joint
     */
    public int getValue(){
        return DegreesOfFreedom.opposite(getDegreesOfFreedom()).size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param pPos position of the joint within the system
     * @param pValue degrees of freedom
     */
    public Joint(SystemPos pPos, Collection<DegreesOfFreedom> pValue){
        super();
        this.pos = pPos;
        this.degreesOfFreedom = pValue;
        this.support = false;
    }

    /**
     * @param pValue value of the new joint
     */
    public Joint(Collection<DegreesOfFreedom> pValue){
        this(new SystemPos(0,0), pValue);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
