package statics.app.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import statics.app.model.SystemPos;

import static org.junit.jupiter.api.Assertions.*;

class ViewStateTest {

    @Test
    void toScreenPos() {
        ViewState vs = new ViewState();
        vs.setOffX(-500+(int)(Math.round(Math.random()*1000)));
        vs.setOffY(-500+(int)(Math.round(Math.random()*1000)));
        vs.setScale(50+Math.random()*50);

        int x = (int)(Math.round(Math.random()*1000));
        int y = (int)(Math.round(Math.random()*1000));
        ScreenPos ScP = new ScreenPos(x,y);
        SystemPos SyP = vs.toSysPos(ScP);

        Assertions.assertEquals(vs.toScreenPos(SyP),ScP);
    }

    @Test
    void toSysPos() {
        ViewState vs = new ViewState();
        vs.setOffX(-500+(int)(Math.round(Math.random()*1000)));
        vs.setOffY(-500+(int)(Math.round(Math.random()*1000)));
        vs.setScale(50d);

        double x = (Math.round(Math.random()*1000));
        double y = (Math.round(Math.random()*1000));
        SystemPos SyP = new SystemPos(x,y);
        ScreenPos ScP = vs.toScreenPos(SyP);

        Assertions.assertEquals(vs.toSysPos(ScP),SyP);
    }
}