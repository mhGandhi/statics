package statics.app.view;

/**
 * holds single value of type I for defining rules for the view
 *
 * @param <I> type of the value
 *
 * @author Adrian Akipi
 */
public class ViewRule <I>{
    private I value;

    public I getValue(){
        return this.value;
    }

    public void setValue(I pVal){
        this.value = pVal;
    }

    public ViewRule(I pInitVal){
        this.value = pInitVal;
    }
}
