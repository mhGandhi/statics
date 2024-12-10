package statics.app.view.components;

import statics.app.view.RedrawModes;

import java.awt.*;

public abstract class VComponent implements IComponent{
    private int id;

    public VComponent(){
        this.id = -1;
    }

    @Override
    public void draw(Graphics2D g2d, RedrawModes redrawMode) {

    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int idd){
        this.id=idd;
    }
}
