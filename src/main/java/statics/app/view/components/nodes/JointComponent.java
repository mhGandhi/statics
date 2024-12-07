package statics.app.view.components.nodes;

import statics.app.model.nodes.Joint;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class JointComponent extends NodeComponent {
    public JointComponent(ViewState pVs, Joint n) {
        super(pVs, n);
        setPos(n.getPos());
    }

    @Override
    public Rectangle getBounds() {
        ScreenPos sp = vs.toScreenPos(systemPos);
        int sc = (int)Math.round(vs.getScale()*scale*0.2);
        return new Rectangle(sp.getX()-sc/2,sp.getY()-sc/2,sc,sc);
    }

    @Override
    public boolean contains(ScreenPos sp) {
        int distX = sp.getX()-screenPos.getX();
        int distY = sp.getY()-screenPos.getY();
        double dist = Math.sqrt(distX*distX+distY*distY);
        return dist<=currentScreenScale*0.2;
    }

    @Override
    protected void drawNode(Graphics2D g2d) {
        //g2d.setPaint(vs.getColorScheme().drawBg1);
        //int diam = (int)Math.round(currentScreenScale);
        //g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);


        g2d.setPaint(Color.GREEN);
        //g2d.draw(getBounds());
        g2d.drawString("id "+id, screenPos.getX(), screenPos.getY());
    }

    @Override
    protected void drawHighlight(Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawHighlight);
        int diam = (int)Math.round(currentScreenScale*0.2);
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);
    }
}
