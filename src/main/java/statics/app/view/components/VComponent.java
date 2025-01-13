package statics.app.view.components;

import statics.app.view.RedrawModes;
import statics.app.view.ViewState;

import java.awt.*;
import java.util.List;

public abstract class VComponent implements IComponent{
    private int id;
    protected ViewState vs;

    public VComponent(ViewState pvs){
        this.id = -1;
        this.vs = pvs;
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes pRedrawMode) {
        switch (pRedrawMode){
            case RESCALE:
                calcScale();
            case MOVED:
                calcPos();
            case UNMOVED:
                boolean drawBounds = false;
                try{
                    drawBounds = (Boolean)vs.getViewRule("drawBounds").getValue();
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(drawBounds)
                    drawBounds(g2d);

                drawFinal(g2d);

                boolean drawHighlight = false;
                try{
                    java.util.List<Integer> highlightedComponents = (List<Integer>)vs.getViewRule("highlightedComponents").getValue();
                    if(highlightedComponents.contains(this.id))
                        drawHighlight = true;
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                if(drawHighlight)
                    drawHighlight(g2d);

                boolean drawMouseOver = false;
                try{
                    if((int)vs.getViewRule("mouseOverComponent").getValue() == this.id)
                        drawMouseOver = true;
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                if(drawMouseOver)
                    drawMouseOver(g2d);

            default:
        }
    }

    protected void drawMouseOver(Graphics2D g2d){

    }

    protected void drawHighlight(Graphics2D g2d) {
    }

    protected void drawFinal(Graphics2D g2d) {
    }

    protected void drawBounds(Graphics2D g2d){

    }

    protected void calcPos() {
    }

    protected void calcScale() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int idd){
        this.id=idd;
    }
}
