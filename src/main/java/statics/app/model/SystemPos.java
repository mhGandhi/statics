package statics.app.model;

import statics.app.Pos;

import java.util.Objects;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SystemPos pos){
            return Objects.equals(this.getX(), pos.getX()) && Objects.equals(this.getY(), pos.getY());
        }else{
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SystemPos clone(){
        return new SystemPos(getX(),getY());
    }
}
