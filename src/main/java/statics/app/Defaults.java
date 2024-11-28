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
                Color.WHITE,Color.BLACK,Color.GRAY,Color.LIGHT_GRAY,Color.BLACK
        );
    }
}
