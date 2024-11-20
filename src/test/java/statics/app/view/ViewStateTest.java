package statics.app.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import statics.app.model.SystemPos;

class ViewStateTest {

    private static ScreenPos getRandomScreenPos(){
        int x = -500+(int)(Math.round(Math.random()*1000));
        int y = -500+(int)(Math.round(Math.random()*1000));
        return new ScreenPos(x,y);
    }

    private static SystemPos getRandomSystemPos(){
        double x = -500+(Math.round(Math.random()*1000));
        double y = -500+(Math.round(Math.random()*1000));
        return new SystemPos(x,y);
    }

    private static ViewState getRandomViewstate(){
        ViewState vs = new ViewState();
        vs.setOffX(-500+(int)(Math.round(Math.random()*1000)));
        vs.setOffY(-500+(int)(Math.round(Math.random()*1000)));
        vs.setScale(10d+Math.random()*50d);

        return vs;
    }

    private static ViewState getVS(){
        return new ViewState();
    }

    @Test
    void toScreenPos() {
        for (int i = 0; i < 10; i++) {
            ViewState vs = getRandomViewstate();

            ScreenPos ScP = getRandomScreenPos();
            SystemPos SyP = vs.toSysPos(ScP);

            Assertions.assertEquals(vs.toScreenPos(SyP),ScP);
        }
    }

    @Test
    void toSysPos() {
        for (int i = 0; i < 10; i++) {
            ViewState vs = getRandomViewstate();

            SystemPos SyP = getRandomSystemPos();
            ScreenPos ScP = vs.toScreenPos(SyP);
            //System.out.println(SyP +" -> "+ScP+" -> "+ vs.toSysPos(ScP));
            Assertions.assertEquals(vs.toSysPos(ScP),SyP);
        }
    }
}