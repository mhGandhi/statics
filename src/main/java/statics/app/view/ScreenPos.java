package statics.app.view;

import statics.app.Pos;

import java.util.Objects;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ScreenPos pos){
            return Objects.equals(this.getX(), pos.getX()) && Objects.equals(this.getY(), pos.getY());
        }else{
            return false;
        }
    }
}
