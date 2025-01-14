package statics.app.view;

import statics.app.Pos;
import statics.app.model.SystemPos;

import java.awt.event.MouseEvent;
import java.util.Collection;
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
     * create new Pos based on MouseEvent
     *
     * @param e MouseEvent to provide position
     */
    public ScreenPos(MouseEvent e) {
        super(e.getX(), e.getY());
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

    /**
     * todo comment
     */
    public void set(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ScreenPos clone(){
        return new ScreenPos(getX(),getY());
    }

    public static ScreenPos from(double[] pAr) {
        return new ScreenPos((int)Math.round(pAr[0]),(int)Math.round(pAr[1]));
    }

    public ScreenPos add(ScreenPos pPos) {
        return new ScreenPos(getX()+pPos.getX(),getY()+pPos.getY());
    }
    public ScreenPos sub(ScreenPos pPos){
        return new ScreenPos(getX()-pPos.getX(),getY()-pPos.getY());
    }

    public double distTo(ScreenPos mp) {
        ScreenPos off = this.sub(mp);
        return Math.sqrt(off.getX()* off.getX()+off.getY()* off.getY());
    }
}
