package statics.app.view.components.nodes;

import org.jetbrains.annotations.NotNull;
import statics.app.model.nodes.RotaryJoint;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class RotaryJointComponent extends JointComponent{
    public RotaryJointComponent(ViewState pVs, RotaryJoint n) {
        super(pVs, n);
    }

    @Override
    public Rectangle getBounds() {
        ScreenPos sp = vs.toScreenPos(systemPos);
        int sc = (int)Math.round(vs.getScale()*1);
        return new Rectangle(sp.getX()-sc/2,sp.getY()-sc/2,sc,sc);
    }

    @Override
    public boolean contains(@NotNull ScreenPos sp) {
        int distX = sp.getX()-screenPos.getX();
        int distY = sp.getY()-screenPos.getY();
        double dist = Math.sqrt(distX*distX + distY*distY);
        //System.out.println(dist+"  "+currentScreenScale);
        return dist<=vs.getScale()/2.0;
    }

    @Override
    protected void drawFinal(@NotNull Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawBg1);
        int diam = (int)Math.round(vs.getScale());
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);
    }

    @Override
    protected void drawHighlight(@NotNull Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawHighlight);
        int diam = (int)Math.round(vs.getScale()+5);
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);
    }

    @Override
    protected void drawBounds(@NotNull Graphics2D g2d) {
        g2d.setPaint(Color.GREEN);
        g2d.draw(getBounds());
        g2d.drawString("id "+ nodeId, screenPos.getX(), screenPos.getY());
    }
}
