package statics.app.view.components.nodes;

import statics.app.model.SystemPos;
import statics.app.model.nodes.Node;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.VComponent;

import java.awt.*;

public abstract class NodeComponent extends VComponent {
    protected SystemPos systemPos;
    protected ScreenPos screenPos;
    public int nodeId;
    public NodeComponent(ViewState pVs, Node n){
        super(pVs);
        setPos(new SystemPos(0,0));
        nodeId = n.getId();
    }

    @Override
    public ComponentLayers getLayer() {
        return ComponentLayers.NODES;
    }

    @Override
    protected void calcPos(){
        this.screenPos = vs.toScreenPos(this.systemPos);
    }

    protected void setPos(SystemPos pPos){
        systemPos = new SystemPos(pPos.getX(), pPos.getY());
        calcScale();
        calcPos();
    }
}