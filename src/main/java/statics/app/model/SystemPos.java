package statics.app.model;

import statics.app.Pos;

/**
 * position within the System of the file
 *
 * @author Adrian Akipi
 */
public class SystemPos extends Pos<Double> {
    /**
     * create new Pos
     *
     * @param pX x-coordinate
     * @param pY y-coordinate
     */
    public SystemPos(double pX, double pY) {
        super(pX, pY);
    }
}
