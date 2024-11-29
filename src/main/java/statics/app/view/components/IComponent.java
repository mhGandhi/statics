package statics.app.view.components;

import statics.app.view.RedrawModes;

import java.awt.*;

//todo
public interface IComponent {
    Rectangle getBounds();

    void draw(Graphics2D g2d, RedrawModes redrawMode);

    int getLayer();
}
