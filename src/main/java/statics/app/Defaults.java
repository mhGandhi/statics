package statics.app;

import statics.app.view.ColorScheme;
import statics.json.JsonObject;

import java.awt.*;

/**
 * central management of defaults
 *
 * @author Adrian Akipi
 */
public class Defaults {

    /**
     * @return hardcoded default color scheme
     */
    public static ColorScheme getColorScheme(){
        return new ColorScheme(
                new GradientPaint(
                        0, 0, Color.WHITE,
                        0, 0,Color.LIGHT_GRAY
                ),
                Color.BLACK,
                Color.GRAY,
                Color.BLUE,
                new Color(Color.DARK_GRAY.getRed(),Color.DARK_GRAY.getGreen(),Color.DARK_GRAY.getBlue(),65),
                Color.LIGHT_GRAY,
                Color.BLACK
        );
    }

    /**
     * @return hardcoded default color for missing colors
     */
    public static Color getMissingColor(){
        return new Color(255,0,220);
    }

    /**
     * @return hardcoded default settings
     */
    public static JsonObject getConfig(){
        JsonObject defaultConfig = new JsonObject();



        return defaultConfig;
    }
}
