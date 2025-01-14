package statics.app.view.components.nodes;

import org.jetbrains.annotations.NotNull;
import statics.app.model.nodes.Joint;
import statics.app.view.PaintingUtil;
import statics.app.view.ScreenPos;
import statics.app.view.ViewRule;
import statics.app.view.ViewState;

import java.awt.*;
import java.awt.event.MouseEvent;

public class JointComponent extends NodeComponent {
    public double angle;

    public JointComponent(ViewState pVs, Joint n) {
        super(pVs, n);
        setPos(n.getPos());
        angle = n.getAngle();
    }

    @Override
    public Rectangle getBounds() {
        ScreenPos sp = vs.toScreenPos(systemPos);
        int sc = (int)Math.round(vs.getScale()*0.2);
        return new Rectangle(sp.getX()-sc/2,sp.getY()-sc/2,sc,sc);
    }

    @Override
    public boolean contains(@NotNull ScreenPos sp) {
        int distX = sp.getX()-screenPos.getX();
        int distY = sp.getY()-screenPos.getY();
        double dist = Math.sqrt(distX*distX+distY*distY);
        //System.out.println(dist+"  "+currentScreenScale*0.2);
        return dist<=(vs.getScale()*0.2)/2;
    }

    @Override
    protected void drawFinal(Graphics2D g2d) {
        //g2d.setPaint(vs.getColorScheme().drawBg1);
        //int diam = (int)Math.round(currentScreenScale);
        //g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);
    }

    @Override
    protected void drawMouseOver(Graphics2D g2d) {
        //todo other color
        g2d.setPaint(vs.getColorScheme().drawHighlight);
        int diam = (int)Math.round(vs.getScale()*0.2);
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);
    }

    @Override
    protected void drawHighlight(@NotNull Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawHighlight);
        int diam = (int)Math.round(vs.getScale()*0.2);
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);
    }

    @Override
    protected void drawBounds(@NotNull Graphics2D g2d) {
        g2d.setPaint(Color.GREEN);
        g2d.draw(getBounds());
        g2d.drawString("id "+ nodeId, screenPos.getX(), screenPos.getY());
    }
}
