package statics.app.view.components.edges;

import statics.app.model.edges.IEdge;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;

import java.awt.*;

public class EdgeComponent implements IComponent {
    protected ViewState vs;
    public EdgeComponent(ViewState pVs, IEdge e) {
        this.vs = pVs;
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes redrawMode) {

    }

    @Override
    public ComponentLayers getLayer() {
        return ComponentLayers.EDGES;
    }
}
