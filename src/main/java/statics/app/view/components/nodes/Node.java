package statics.app.view.components.nodes;

import statics.app.model.nodes.INode;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;

import java.awt.*;

public class Node implements IComponent {
    public Node(ViewState vs, INode n) {
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
        return null;
    }

    @Override
    public boolean contains(ScreenPos sp) {
        return false;
    }
}
