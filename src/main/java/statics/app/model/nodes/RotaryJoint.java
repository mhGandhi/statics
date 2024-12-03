package statics.app.model.nodes;

import statics.app.model.SystemPos;

public class RotaryJoint extends Joint{
    /**
     * @param pPos   position of the joint within the system
     */
    public RotaryJoint(SystemPos pPos) {
        super(pPos, 2);
    }
}
