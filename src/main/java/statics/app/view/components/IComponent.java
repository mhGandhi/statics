package statics.app.view.components;

import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;

import java.awt.*;

//todo
public interface IComponent {
    Rectangle getBounds();

    void draw(Graphics2D g2d, RedrawModes redrawMode);

    int getLayer();

    boolean contains(ScreenPos pPos);
}
