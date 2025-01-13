package statics.app.view.components.nodes.supports;

import org.jetbrains.annotations.NotNull;
import statics.app.model.nodes.Joint;
import statics.app.view.PaintingUtil;
import statics.app.view.ViewState;
import statics.app.view.components.nodes.RotaryJointComponent;

import java.awt.*;

public class RotarySupportComponent extends RotaryJointComponent {
    public RotarySupportComponent(ViewState pVs, Joint n) {
        super(pVs, n);
    }

    @Override
    protected void drawFinal(@NotNull Graphics2D g2d) {
        g2d.setPaint(vs.getColorScheme().drawBg1);
        int diam = (int)Math.round(vs.getScale());
        PaintingUtil.drawTriangleOnPoint(g2d, screenPos, 0d, diam*2);

        super.drawFinal(g2d);
    }
}
