package statics.app.view;

import statics.app.IActionHandler;
import statics.app.model.SystemPos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//todo comment
public class StaticsPanel extends JPanel {
    private final ViewState vs;

    public StaticsPanel(ViewState pViewState, IActionHandler pActionHandler) {
        this.vs = pViewState;
        this.setFocusable(true);

        this.addMouseMotionListener((MouseMotionListener) pActionHandler);
        this.addMouseListener((MouseListener) pActionHandler);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.addRenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        {//draw background
            {//bg color
                GradientPaint bgPaint = (GradientPaint) vs.getColorScheme().background;
                GradientPaint bgPaintWithCoordinates = new GradientPaint(
                        -getWidth(),-getHeight(),bgPaint.getColor1(),
                        getWidth()*2,getHeight()*2,bgPaint.getColor2()
                );
                g2d.setPaint(bgPaintWithCoordinates);

                g2d.fillRect(0,0,getWidth(),getHeight());
            }
            {//bg grid
                g2d.setPaint(vs.getColorScheme().drawBg2);
                double gridSize = 2.5;
                {//vertical gridlines
                    for(
                            int i = -vs.getOffX()%(int)Math.round(gridSize* vs.getScale());
                            i <= getWidth();
                            i += (int)Math.round(gridSize*vs.getScale())
                    ){
                        g2d.drawLine(i,0,i,getHeight());
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
                            i <= getHeight();
                            i += (int)Math.round(gridSize*vs.getScale())
                    ){
                        g2d.drawLine(0,i,getWidth(),i);
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

            ScreenPos tmp = vs.toScreenPos(new SystemPos(0,0));
            g2d.fillOval(tmp.getX(),tmp.getY(),vs.getScale(),vs.getScale());
        }
    }
}
