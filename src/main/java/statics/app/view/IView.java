package statics.app.view;

import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;

import java.util.Collection;

//todo comment
public interface IView {

    void repaint(RedrawModes pRedrawMode);

    void setNodes(Collection<INode> nodes);

    void setEdges(Collection<IEdge> edges);
}
