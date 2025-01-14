package statics.app.view.components.nodes;

import org.jetbrains.annotations.NotNull;
import statics.app.model.nodes.Joint;
import statics.app.view.PaintingUtil;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class RotaryJointComponent extends JointComponent{
    public RotaryJointComponent(ViewState pVs, Joint n) {
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
    protected void drawMouseOver(Graphics2D g2d) {
        //todo different color
        g2d.setPaint(vs.getColorScheme().drawHighlight);
        int diam = (int)Math.round(vs.getScale()+5);
        g2d.drawOval(screenPos.getX()-diam/2,screenPos.getY()-diam/2, diam,diam);

        ScreenPos mp = new ScreenPos(
                (int)vs.getViewRule("mouseX").getValue(),
                (int)vs.getViewRule("mouseY").getValue()
        );
        if(!this.contains(mp)){
            double angle = PaintingUtil.angleNegYToPoint(screenPos,mp);
            double[] edgeOffset = PaintingUtil.getRadiusTipOffset(360+270-angle,diam/2d);
            ScreenPos edgeP = new ScreenPos(
                    screenPos.getX()+(int)Math.round(edgeOffset[0]),
                    screenPos.getY()+(int)Math.round(edgeOffset[1])
            );
            g2d.setPaint(vs.getColorScheme().drawBg2);
            //PaintingUtil.drawArrow(g2d, screenPos, mp, diam/2d);
            g2d.drawLine(edgeP.getX(), edgeP.getY(), mp.getX(), mp.getY());
            g2d.drawOval(mp.getX()-5,mp.getY()-5,10,10);
        }
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
