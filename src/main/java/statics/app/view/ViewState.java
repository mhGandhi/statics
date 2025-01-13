package statics.app.view;

import org.jetbrains.annotations.Nullable;
import statics.app.model.SystemPos;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * keeps track of the state of the view
 *
 * @author Adrian Akipi
 */
public class ViewState {
    /**
     * minimum value for the scale
     * should always be bigger than zero
     */
    public final int MIN_SCALE = 5;//>0

    /**
     * maximum value for the scale
     */
    public final int MAX_SCALE = 500;//todo zu constructor vlt?


    /**
     * current x-offset from [0|y] in the view
     */
    private int offX;

    /**
     * current y-offset from [x|0] in the view
     */
    private int offY;

    /**
     * current scale of the view
     */
    private int scale;

    /**
     * maps the keys of all kinds of rules to their value
     */
    private final Map<String,ViewRule<?>> rules;

    /**
     * color scheme applied to the current view instance
     */
    private final ColorScheme colorScheme;

    /**
     * locale of the current view instance
     */
    private final ResourceBundle lang;

    /**
     * height of the display
     */
    private int height;

    /**
     * width of the display
     */
    private int width;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return offset on the x-axis
     */
    public int getOffX(){
        return this.offX;
    }
    /**
     * @param pOX new offset on the x-axis
     */
    public void setOffX(int pOX){
        this.offX = pOX;
    }

    /**
     * @return offset on the y-axis
     */
    public int getOffY(){
        return this.offY;
    }
    /**
     * @param pOY new offset on the y-axis
     */
    public void setOffY(int pOY){
        this.offY = pOY;
    }

    /**
     * @return scale of the view
     */
    public int getScale(){
        return this.scale;
    }
    /**
     * @param pScale new scale of the view
     * @throws ScaleOutOfBoundsException thrown if @pScale is out of bounds specified by MIN_SCALE and MAX_SCALE
     */
    public void setScale(int pScale) throws ScaleOutOfBoundsException{
        if(pScale<MIN_SCALE){
            throw new ScaleOutOfBoundsException("Scale "+pScale+" has to be at least "+MIN_SCALE);

        }
        if(pScale>MAX_SCALE)throw new ScaleOutOfBoundsException("Scale "+pScale+" has to be at most "+MAX_SCALE);
        this.scale = pScale;
    }

    /**
     * @param pKey key of the sought after rule
     * @return view rule with key @pKey
     */
    public ViewRule<?> getViewRule(String pKey){
        return rules.get(pKey);
    }

    /**
     * @param pKey specifies key for the rule to be set
     * @param pViewRule specifies the view rule holding the value
     * @throws ViewRuleKeyExistsException thrown if another rule with the same key already exists
     */
    public void setViewRule(String pKey, ViewRule<?> pViewRule)throws ViewRuleKeyExistsException {
        if(this.rules.containsKey(pKey))throw new ViewRuleKeyExistsException("Key "+pKey+" already taken in ViewRules");
        this.rules.put(pKey, pViewRule);
    }

    /**
     * @return this instances color scheme
     */
    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    /**
     * @return this instances locale
     */
    public ResourceBundle getLang(){ return this.lang;}

    /**
     * @return current height of the display
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * @param pH new height of the display
     */
    public void setHeight(int pH){
        this.height = pH;
    }

    /**
     * @return current width of the display
     */
    public int getWidth(){
        return this.width;
    }
    /**
     * @param pW new width of the display
     */
    public void setWidth(int pW){
        this.width = pW;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param pColorScheme color scheme for the new instance
     * @param pLang locale for the view state instance
     */
    public ViewState(@Nullable ColorScheme pColorScheme,@Nullable ResourceBundle pLang){

        this.offX = 0;
        this.offY = 0;
        this.scale = 50;//todo define default zoom

        this.rules = new TreeMap<String, ViewRule<?>>();

        this.colorScheme = Objects.requireNonNullElseGet(pColorScheme, ColorScheme::getDefault);
        this.lang = pLang;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * converts system coordinates to screen coordinates under the circumstances provided by the scale and x+y-offsets
     * @param pSystemPos system position to translate
     * @return screen coordinates of the given system coordinates
     */
    public ScreenPos toScreenPos(SystemPos pSystemPos){
        if(pSystemPos==null)throw new NullPointerException();

        int x = (int)Math.round((pSystemPos.getX())*getScale()-getOffX());
        int y = (int)Math.round((pSystemPos.getY())*getScale()-getOffY());

        return new ScreenPos(x,y);
    }

    /**
     * converts screen coordinates to system coordinates under the circumstances provided by the scale and x+y-offsets
     * @param pScreenPos screen position to translate
     * @return system coordinates of the given screen coordinates
     */
    public SystemPos toSysPos(ScreenPos pScreenPos){
        if(pScreenPos==null)throw new NullPointerException();

        double x = ((double)(pScreenPos.getX()+getOffX()))/getScale();
        double y = ((double)(pScreenPos.getY()+getOffY()))/getScale();

        return new SystemPos(x,y);
    }

    public String t(String pDisplay) {
        if(lang == null){
            return "["+pDisplay+"]";
        }
        try{
            return lang.getString(pDisplay);
        }catch(Exception e){
            try {
                System.err.println("[" + pDisplay + "] not translated in package " + lang.getBaseBundleName());
            }catch(Exception ex){
                System.err.println("[" + pDisplay + "] not translated in current package");
            }
            return "["+pDisplay+"]";
        }
    }
}
