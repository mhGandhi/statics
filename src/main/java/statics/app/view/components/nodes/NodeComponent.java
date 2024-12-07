package statics.app.view.components.nodes;

import statics.app.model.SystemPos;
import statics.app.model.nodes.Node;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;

import java.awt.*;
import java.util.List;

public abstract class NodeComponent implements IComponent {
    protected SystemPos systemPos;
    protected ScreenPos screenPos;
    protected double currentScreenScale;
    protected ViewState vs;
    protected double scale;
    public int id;
    public NodeComponent(ViewState pVs, Node n){
        this.vs = pVs;
        scale = 1;
        setPos(new SystemPos(0,0));
        id = n.getId();
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
                try{
                    List<Integer> hns = (List<Integer>)vs.getViewRule("highlightedJoints").getValue();
                    if(hns.contains(this.id))
                        drawHighlight(g2d);
                }catch(Exception ex){
                    ex.printStackTrace();
                }

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

    protected void drawHighlight(Graphics2D g2d){
        //draw Highlight of note
    }

    protected void setPos(SystemPos pPos){
        systemPos = new SystemPos(pPos.getX(), pPos.getY());
        calcScale();
        calcPos();
    }
}