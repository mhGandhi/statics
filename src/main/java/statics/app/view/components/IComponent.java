package statics.app.view.components;

import statics.app.view.RedrawModes;
import statics.app.view.ScreenPos;

import java.awt.*;

//todo
public interface IComponent{
    default Rectangle getBounds(){
        return new Rectangle(0,0,1,1);
    };

    void draw(Graphics2D g2d, RedrawModes redrawMode);

    default ComponentLayers getLayer(){
        return ComponentLayers.NONE;
    };

    default boolean contains(ScreenPos sp){
        return false;
    };
}