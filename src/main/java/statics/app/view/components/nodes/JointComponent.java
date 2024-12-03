package statics.app.view.components.nodes;

import statics.app.model.nodes.Joint;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class JointComponent extends NodeComponent {
    public JointComponent(ViewState pVs, Joint n) {
        super(pVs, n.getPos());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(
                screenPos.getX()-(int)Math.round(currentScreenScale/2),
                screenPos.getY()-(int)Math.round(currentScreenScale/2),
                (int)Math.round(currentScreenScale),
                (int)Math.round(currentScreenScale)
        );
    }

    @Override
    public boolean contains(ScreenPos sp) {
        int distX = sp.getX()-screenPos.getX()+(int)Math.round(currentScreenScale/2);
        int distY = sp.getY()-screenPos.getY()+(int)Math.round(currentScreenScale/2);
        double dist = Math.sqrt(distX*distX+distY*distY);
        return dist<=currentScreenScale;
    }

    @Override
    protected void drawNode(Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawBg1);
        int diam = (int)Math.round(currentScreenScale);
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);

        g2d.setPaint(Color.GREEN);
        g2d.draw(getBounds());
    }
}
