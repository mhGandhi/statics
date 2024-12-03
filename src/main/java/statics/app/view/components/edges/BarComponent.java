package statics.app.view.components.edges;

import statics.app.model.SystemPos;
import statics.app.model.edges.Bar;
import statics.app.model.edges.IEdge;
import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class BarComponent extends EdgeComponent{
    private SystemPos pos1;
    private SystemPos pos2;
    protected ScreenPos sPos1;
    protected ScreenPos sPos2;
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
        sPos1 = vs.toScreenPos(pos1);
        sPos2 = vs.toScreenPos(pos2);
    }

    private void drawEdge(Graphics2D g2d){
        //draw Edge
    }
}
