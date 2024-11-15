package statics.app;

/**
 * superclass for positions
 * @param <T> type of the Numbers accepted in the vector space
 */
public class Pos<T extends Number> {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * x-coordinate
     */
    private T x;

    /**
     * y-coordinate
     */
    private T y;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return x-coordinate
     */
    public T getX(){
        return this.x;
    }

    /**
     * @return y-coordinate
     */
    public T getY(){
        return this.y;
    }

    /**
     * @param pX new x-coordinate
     */
    public void setX(T pX){
        this.x = pX;
    }

    /**
     * @param pY new y-coordinate
     */
    public void setY(T pY){
        this.y = pY;
    }

    /**
     * create new Pos
     * @param pX x-coordinate
     * @param pY y-coordinate
     */
    public Pos(T pX, T pY){
        this.x = pX;
        this.y = pY;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "["+x+"|"+y+"]";
    }
}
