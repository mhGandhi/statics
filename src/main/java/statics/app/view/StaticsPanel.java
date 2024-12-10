package statics.app.view;

import statics.app.IActionHandler;
import statics.app.model.SystemPos;
import statics.app.view.components.*;
import statics.app.view.components.nodes.JointComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//todo comment
public class StaticsPanel extends JPanel {
    private final ViewState vs;
    private List<IComponent> components;
    private RedrawModes redrawMode;

    public StaticsPanel(ViewState pViewState, IActionHandler pActionHandler) {
        this.vs = pViewState;
        this.components = new LinkedList<>();
        this.redrawMode = RedrawModes.RESCALE;
        this.setFocusable(true);

        this.addMouseMotionListener((MouseMotionListener) pActionHandler);
        this.addMouseListener((MouseListener) pActionHandler);
        this.addComponentListener((ComponentListener) pActionHandler);
        this.addKeyListener((KeyListener) pActionHandler);
        this.addMouseWheelListener((MouseWheelListener) pActionHandler);

        vs.setViewRule("highlightedComponents", new ViewRule<>(new LinkedList<Integer>()));
        vs.setViewRule("drawBounds", new ViewRule<>(false));

        addComponent(new Grid(vs));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        {//draw background
            {//bg color
                GradientPaint bgPaint = (GradientPaint) vs.getColorScheme().background;
                GradientPaint bgPaintWithCoordinates = new GradientPaint(
                        -getWidth(), -getHeight(), bgPaint.getColor1(),
                        getWidth() * 2, getHeight() * 2, bgPaint.getColor2()
                );
                g2d.setPaint(bgPaintWithCoordinates);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }

            g2d.setColor(Color.RED);//todo remove
            ScreenPos tmp = vs.toScreenPos(new SystemPos(0,0));
            g2d.drawOval((int)(tmp.getX()-vs.getScale()*5*0.5),(int)(tmp.getY()-vs.getScale()*5*0.5),vs.getScale()*5,vs.getScale()*5);


            {//draw components
                this.components.sort(Comparator.comparing(IComponent::getLayer));
                for(IComponent comp: this.components){
                    comp.draw(g2d,this.redrawMode);//todo maybe not always calc everything
                }
            }
        }


    }

    public void repaint(RedrawModes pRedrawMode) {
        this.redrawMode = pRedrawMode;
        repaint();
    }

    public void eraseNodes(){
        for (IComponent c : this.components){
            if(c.getLayer()== ComponentLayers.NODES){
                this.components.remove(c);
            }
        }
    }
    public void eraseEdges(){
        this.components.removeIf(c -> c.getLayer() == ComponentLayers.EDGES);
    }

    public void addComponent(IComponent c)throws NullPointerException{
        try{
            if(c==null)throw new NullPointerException("added component shall not be null");
        }catch(NullPointerException ne){
            ne.printStackTrace();
            return;
        }

        int id = -1;
        for (IComponent _ : components){
            id++;
            boolean taken = false;
            for(IComponent comp : components){
                if(comp.getId() == id){
                    taken = true;
                    break;
                }
            }
            if(!taken)break;
        }
        c.setId(id);

        this.components.add(c);
    }

    public void addComponent(Object o) {
        try{
            addComponent(ComponentDispatcher.get(vs,o));
        }catch(NoConversionToComponentException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Collection<Integer> getComponentsAt(ScreenPos screenPos) {
        List<Integer> ret = new LinkedList<>();
        for (IComponent c : this.components){
            if (c.contains(screenPos))
                ret.add(c.getId());
        }
        return (Collection<Integer>)ret;
    }

    public Collection<Integer> getComponentsAt(Rectangle pRect) {
        //todo
        return List.of();
    }
}
