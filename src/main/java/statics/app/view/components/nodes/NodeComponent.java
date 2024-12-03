package statics.app.view.components.nodes;

import statics.app.model.SystemPos;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;

import java.awt.*;

public abstract class NodeComponent implements IComponent {
    protected SystemPos systemPos;
    protected ScreenPos screenPos;
    protected double currentScreenScale;
    protected ViewState vs;
    protected double scale;
    public NodeComponent(ViewState pVs, SystemPos pos){
        this.vs = pVs;
        systemPos = pos;
        scale = 1;
        calcScale();
        calcPos();
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes pRedrawMode) {
        switch (pRedrawMode){
            case RESCALE:
                calcScale();
            case MOVED:
                calcPos();
            case UNMOVED:
                drawNode(g2d);
            default:
        }
    }

    @Override
    public ComponentLayers getLayer() {
        return ComponentLayers.NODES;
    }

    @Override
    public boolean contains(ScreenPos sp) {
        return false;
    }

    protected void calcScale(){
        currentScreenScale = vs.getScale()*scale;
    }

    protected void calcPos(){
        this.screenPos = vs.toScreenPos(this.systemPos);
    }

    protected void drawNode(Graphics2D g2d){
        //draw based on screenPos and currentScreenScale
    }
}