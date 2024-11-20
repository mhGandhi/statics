package statics.app.model.nodes;

import statics.app.model.SystemPos;

import java.util.Vector;

/**
 * portrays a joint in a construction
 */
public class Joint extends Node {
    /**
     * position within system
     */
    private SystemPos pos;

    /**
     * value of the joint
     */
    private final int value;
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
    public int getValue(){
        return this.value;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param pPos position of the joint within the system
     * @param pValue value of the new joint
     */
    public Joint(SystemPos pPos, int pValue){
        super();
        this.pos = pPos;
        this.value = pValue;
    }

    /**
     * @param pValue value of the new joint
     */
    public Joint(int pValue){
        this(new SystemPos(0,0), pValue);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
