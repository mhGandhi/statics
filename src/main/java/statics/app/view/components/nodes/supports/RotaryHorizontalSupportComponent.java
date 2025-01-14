package statics.app.view.components.nodes.supports;

import org.jetbrains.annotations.NotNull;
import statics.app.model.nodes.Joint;
import statics.app.view.PaintingUtil;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;

public class RotaryHorizontalSupportComponent extends RotarySupportComponent{
    public RotaryHorizontalSupportComponent(ViewState pVs, Joint n) {
        super(pVs, n);
    }

    protected void drawFinal(@NotNull Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawBg1);
        int diam = (int)Math.round(vs.getScale());
        PaintingUtil.drawTangent(g2d, screenPos, 0d+this.angle, diam*2d, diam*3);

        super.drawFinal(g2d);
    }
}
