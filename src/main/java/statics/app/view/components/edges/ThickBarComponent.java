package statics.app.view.components.edges;

import statics.app.model.edges.Bar;
import statics.app.view.PaintingUtil;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class ThickBarComponent extends BarComponent{
    ScreenPos sPos1A, sPos1B, sPos2A, sPos2B;

    public ThickBarComponent(ViewState pVs, Bar e) {
        super(pVs, e);
    }

    @Override
    protected void calcPos(){
        int diam = (int)Math.round(vs.getScale());

        ScreenPos cPos1 = vs.toScreenPos(pos1);
        ScreenPos cPos2 = vs.toScreenPos(pos2);

        sPos1B = ScreenPos.from(PaintingUtil.getRadiusTipOffset(250+360-PaintingUtil.angleNegYToPoint(cPos1,cPos2),diam/2d)).add(cPos1);
        sPos2A = ScreenPos.from(PaintingUtil.getRadiusTipOffset(250+360-PaintingUtil.angleNegYToPoint(cPos2,cPos1),diam/2d)).add(cPos2);
        sPos1A = ScreenPos.from(PaintingUtil.getRadiusTipOffset(290+360-PaintingUtil.angleNegYToPoint(cPos1,cPos2),diam/2d)).add(cPos1);
        sPos2B = ScreenPos.from(PaintingUtil.getRadiusTipOffset(290+360-PaintingUtil.angleNegYToPoint(cPos2,cPos1),diam/2d)).add(cPos2);
    }

    @Override
    protected void drawEdge(Graphics2D g2d){
        g2d.setPaint(vs.getColorScheme().drawBg1);


        g2d.drawLine(sPos1A.getX(),sPos1A.getY(),sPos2A.getX(),sPos2A.getY());
        g2d.drawLine(sPos1B.getX(),sPos1B.getY(),sPos2B.getX(),sPos2B.getY());
    }
}
