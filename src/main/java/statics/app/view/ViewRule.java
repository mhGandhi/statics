package statics.app.view;

/**
 * holds single value of type I for defining rules for the view
 *
 * @param <I> type of the value
 *
 * @author Adrian Akipi
 */
public class ViewRule <I>{
    /**
     * value of the rule
     */
    private I value;

    /**
     * @return value of the rule
     */
    public I getValue(){
        return this.value;
    }

    /**
     * @param pVal new value of the rule
     */
    public void setValue(I pVal){
        this.value = pVal;
    }

    /**
     * @param pInitVal initial value of the rule
     */
    public ViewRule(I pInitVal){
        this.value = pInitVal;
    }
}
