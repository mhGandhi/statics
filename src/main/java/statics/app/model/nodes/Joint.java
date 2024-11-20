package statics.app.model.nodes;

import statics.app.model.SystemPos;

import java.util.Vector;

//TODO comment
public class Joint extends Node {
    /**
     * saves position within system
     */
    private SystemPos pos;

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

    public int getValue(){
        return this.value;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Joint(SystemPos pPos, int pValue){
        super();
        this.pos = pPos;
        this.value = pValue;
    }

    public Joint(int pValue){
        this(new SystemPos(0,0), pValue);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
