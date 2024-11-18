package statics.app.view;

import java.awt.*;
//todo comment
public class ColorScheme {
    public final Color background;
    public final Color drawBg1;
    public final Color drawBg2;

    public final Color menu;
    public final Color drawMenu;

    public ColorScheme(Color pBackground, Color pDrawBg1, Color pDrawBg2, Color pMenu, Color pDrawMenu) {
        this.background = pBackground;
        this.drawBg1 = pDrawBg1;
        this.drawBg2 = pDrawBg2;
        this.menu = pMenu;
        this.drawMenu = pDrawMenu;
    }

    public static ColorScheme getDefault(){
        return new ColorScheme(
            Color.WHITE,Color.BLACK,Color.GRAY,Color.LIGHT_GRAY,Color.BLACK
        );
    }
    //from file
    //to file
}