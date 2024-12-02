package statics.app.view.components.nodes;

import statics.app.view.RedrawModes;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;

import java.awt.*;

public class Node implements IComponent {
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
}
