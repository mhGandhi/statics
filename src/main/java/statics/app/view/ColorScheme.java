package statics.app.view;

import java.awt.*;

/**
 * defines color scheme for a view state
 *
 * @author Adrian Akipi
 */
public class ColorScheme {
    /**
     * color for backgrounds
     */
    public final Color background;
    /**
     * color for drawing on background
     */
    public final Color drawBg1;
    /**
     * second color for drawing on background
     */
    public final Color drawBg2;

    /**
     * color to draw the background of menus
     */
    public final Color menu;
    /**
     * color for drawing on menus
     */
    public final Color drawMenu;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param pBackground color for backgrounds
     * @param pDrawBg1 color for drawing on background
     * @param pDrawBg2 second color for drawing on background
     * @param pMenu color to draw the background of menus
     * @param pDrawMenu color for drawing on menus
     */
    public ColorScheme(Color pBackground, Color pDrawBg1, Color pDrawBg2, Color pMenu, Color pDrawMenu) {
        this.background = pBackground;
        this.drawBg1 = pDrawBg1;
        this.drawBg2 = pDrawBg2;
        this.menu = pMenu;
        this.drawMenu = pDrawMenu;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return hardcoded default color scheme
     */
    public static ColorScheme getDefault(){
        return new ColorScheme(
            Color.WHITE,Color.BLACK,Color.GRAY,Color.LIGHT_GRAY,Color.BLACK
        );
    }

    //todo from file
    //todo to file
}