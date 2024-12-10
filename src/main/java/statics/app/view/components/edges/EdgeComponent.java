package statics.app.view.components.edges;

import statics.app.model.SystemPos;
import statics.app.model.edges.Edge;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;
import statics.app.view.components.ComponentLayers;
import statics.app.view.components.IComponent;
import statics.app.view.components.VComponent;

import java.awt.*;

public class EdgeComponent extends VComponent {
    protected INode from;
    protected INode to;

    public EdgeComponent(ViewState pVs, Edge e) {
        super(pVs);
        from = e.getStart();
        to = e.getStart();
    }

    @Override
    public ComponentLayers getLayer() {
        return ComponentLayers.EDGES;
    }

}
