package statics.app.view.components.edges;

import statics.app.model.SystemPos;
import statics.app.model.edges.Bar;
import statics.app.model.edges.IEdge;
import statics.app.view.PaintingUtil;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class BarComponent extends EdgeComponent{
    protected SystemPos pos1;
    protected SystemPos pos2;
    private ScreenPos sPos1;
    private ScreenPos sPos2;
    public BarComponent(ViewState pVs, Bar e) {
        super(pVs, e);
        pos1 = e.getStart().getPos();
        pos2 = e.getEnd().getPos();
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes pRedrawMode) {
        switch (pRedrawMode){
            case RESCALE:
            case MOVED:
                calcPos();
            case UNMOVED:
                drawEdge(g2d);
            default:
        }
    }

    protected void calcPos(){
        int diam = (int)Math.round(vs.getScale());

        ScreenPos cPos1 = vs.toScreenPos(pos1);
        ScreenPos cPos2 = vs.toScreenPos(pos2);

        sPos1 = ScreenPos.from(PaintingUtil.getRadiusTipOffset(270+360-PaintingUtil.angleNegYToPoint(cPos1,cPos2),diam/2d)).add(cPos1);
        sPos2 = ScreenPos.from(PaintingUtil.getRadiusTipOffset(270+360-PaintingUtil.angleNegYToPoint(cPos2,cPos1),diam/2d)).add(cPos2);
    }

    protected void drawEdge(Graphics2D g2d){
        g2d.setPaint(vs.getColorScheme().drawBg1);


        g2d.drawLine(sPos1.getX(),sPos1.getY(),sPos2.getX(),sPos2.getY());
    }
}
