package statics.app.view.components.nodes;

import statics.app.model.nodes.INode;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;

import java.awt.*;

public abstract class Node implements IComponent {
    protected ViewState vs;
    public Node(ViewState pVs){
        this.vs = pVs;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes redrawMode) {

    }

    @Override
    public ComponentLayers getLayer() {
        return ComponentLayers.NODES;
    }

    @Override
    public boolean contains(ScreenPos sp) {
        return false;
    }
}
