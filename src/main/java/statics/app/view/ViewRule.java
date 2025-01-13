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

    /**
     * toggle the rule if it is boolean
     * @return whether operation resulted in a change
     */
    public boolean toggle(){
        try{
            toggleViewRule((ViewRule<Boolean>) this);
            return true;
        }catch(ClassCastException ce){
            System.out.println("view rule of type "+value.getClass().toString()+" can not be toggled");
            return false;
        }
    }
    /**
     * toggle a boolean rule
     * @param pVr rule to toggle
     */
    public static void toggleViewRule(ViewRule<Boolean> pVr){
        pVr.setValue(!pVr.getValue());
    }
}
