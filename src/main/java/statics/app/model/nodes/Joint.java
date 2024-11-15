package statics.app.model.nodes;

import statics.app.model.SystemPos;

import java.util.Vector;

public class Joint extends Node {
    /**
     * saves position within system
     */
    private SystemPos pos;

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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Joint(SystemPos pPos){
        super();
        this.pos = pPos;
    }

    public Joint(){
        this(new SystemPos(0,0));
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
