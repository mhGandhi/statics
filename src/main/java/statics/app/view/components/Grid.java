package statics.app.view.components;

import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import javax.swing.*;
import java.awt.*;

public class Grid extends VComponent{
    ViewState vs;

    public Grid(ViewState pVs){
        this.vs = pVs;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(vs.getWidth(),vs.getHeight());
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes redrawMode) {
        JComponent parent;

        {//bg grid
            g2d.setPaint(vs.getColorScheme().backgroundAnnotation);
            double gridSize = 2.5;
            {//vertical gridlines
                for(
                        int i = -vs.getOffX()%(int)Math.round(gridSize* vs.getScale());
                        i <= vs.getWidth();
                        i += (int)Math.round(gridSize*vs.getScale())
                ){
                    g2d.drawLine(i,0,i,vs.getHeight());
                }
            }
                /*//todo add some way to display coords
                for (int i = (int)Math.round((-viewState.getOffsetX())%(2*(viewState.getZoom()*1))); i < getBounds().getWidth(); i+=2*(int)Math.round(viewState.getZoom()*1)) {
                    g2d.drawLine(i, 0, i, (int)getBounds().getHeight());
                    if(viewState.getZoom()>=20&&i>20){
                        Font f = new Font(getViewState().getTheme().getFont(), Font.PLAIN, 20);
                        g2d.setFont(f);
                        g2d.drawString(""+(Math.round((viewState.getOffsetX()+i)/viewState.getZoom())), i+1, 20);
                    }
                }
                */
            {//horizontal gridlines
                for(
                        int i = -vs.getOffY()%(int)Math.round(gridSize* vs.getScale());
                        i <= vs.getHeight();
                        i += (int)Math.round(gridSize*vs.getScale())
                ){
                    g2d.drawLine(0,i,vs.getWidth(),i);
                }
            }
                /*//todo add some way to display coords
                for (int i = (int)Math.round((-viewState.getOffsetY())%(2*(viewState.getZoom()*1))); i < getBounds().getHeight(); i+=2*(int)Math.round(viewState.getZoom()*1)) {
                    g2d.drawLine(0, i, (int)getBounds().getWidth(), i);
                    if(viewState.getZoom()>=20&&i>20){
                        g2d.drawString(""+(Math.round((viewState.getOffsetY()+i)/viewState.getZoom())), 0, i-1);
                    }
                }
                */
        }
    }

    @Override
    public ComponentLayers getLayer() {
        return ComponentLayers.BACKGROUND;
    }

    @Override
    public boolean contains(ScreenPos sp) {
        return getBounds().contains(new Point(sp.getX(), sp.getY()));
    }
}
