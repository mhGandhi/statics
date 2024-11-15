package statics.app.view;

import statics.app.Pos;

/**
 * position on the screen
 *
 * @author Adrian Akipi
 */
public class ScreenPos extends Pos<Integer> {
    /**
     * create new Pos
     *
     * @param pX x-coordinate
     * @param pY y-coordinate
     */
    public ScreenPos(Integer pX, Integer pY) {
        super(pX, pY);
    }
}
