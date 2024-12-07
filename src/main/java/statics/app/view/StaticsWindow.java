package statics.app.view;

import statics.app.IActionHandler;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;

//todo comment
public class StaticsWindow extends JFrame implements IView {
    private ViewState vs;
    private StaticsPanel panel;

    public StaticsWindow(ViewState pViewState, IActionHandler pActionHandler) {
        this.vs = pViewState;
        {
            this.setSize(500,500);//todo settings
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
        }

        this.panel = new StaticsPanel(pViewState, pActionHandler);

        this.add(this.panel,BorderLayout.CENTER);


        this.setVisible(true);
    }

    @Override
    public void repaint(RedrawModes pRedrawMode) {
        panel.repaint(pRedrawMode);
    }

    public void setNodes(Collection<INode> nodes){
        panel.eraseNodes();
        for(INode n : nodes){
            panel.addComponent(n);
        }
    }

    public void setEdges(Collection<IEdge> edges){
        panel.eraseEdges();
        for(IEdge e : edges){
            panel.addComponent(e);
        }
    }

    @Override
    public Collection<Integer> getNodesAt(ScreenPos screenPos) {
        return panel.getNodesAt(screenPos);
    }

    @Override
    public Collection<Integer> getNodesAt(Rectangle pRect) {
        return panel.getNodesAt(pRect);
    }
}
