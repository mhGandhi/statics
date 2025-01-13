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
    private final Collection<DegreesOfFreedom> degreesOfFreedom;
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
     * @return value of the joint
     */
    public Collection<DegreesOfFreedom> getDegreesOfFreedom(){
        return this.degreesOfFreedom;
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
    }

    /**
     * @param pValue value of the new joint
     */
    public Joint(Collection<DegreesOfFreedom> pValue){
        this(new SystemPos(0,0), pValue);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
