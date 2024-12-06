package statics.app.view;

import statics.app.Defaults;

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
    public final Paint background;
    /**
     * color for drawing on background
     */
    public final Paint drawBg1;
    /**
     * second color for drawing on background
     */
    public final Paint drawBg2;
    /**
     * color for drawing highlighted stuff
     */
    public final Paint drawHighlight;

    /**
     * color for drawing annotations on background
     */
    public final Paint backgroundAnnotation;

    /**
     * color to draw the background of menus
     */
    public final Paint menu;
    /**
     * color for drawing on menus
     */
    public final Paint drawMenu;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param pBackground color for backgrounds
     * @param pDrawBg1 color for drawing on background
     * @param pDrawBg2 second color for drawing on background
     * @param pDrawHighlight color for drawing highlighted stuff
     * @param pBackgroundAnnotationPaint color for drawing annotations on background
     * @param pMenu color to draw the background of menus
     * @param pDrawMenu color for drawing on menus
     */
    public ColorScheme(Paint pBackground, Paint pDrawBg1, Paint pDrawBg2, Paint pDrawHighlight, Paint pBackgroundAnnotationPaint, Paint pMenu, Paint pDrawMenu) {
        this.background = pBackground;
        this.drawBg1 = pDrawBg1;
        this.drawBg2 = pDrawBg2;
        this.drawHighlight = pDrawHighlight;
        this.backgroundAnnotation = pBackgroundAnnotationPaint;
        this.menu = pMenu;
        this.drawMenu = pDrawMenu;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return default color scheme
     */
    public static ColorScheme getDefault(){
        return Defaults.getColorScheme();
    }

    //todo from file
    //todo to file
}