package statics.app.view;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * keeps track of the state of the view
 *
 * @author Adrian Akipi
 *///todo comment
public class ViewState {
    public final double MIN_SCALE = 10;//>0
    public final double MAX_SCALE = 500;//todo zu konstruktor vlt?

    private int offX;
    private int offY;
    private double scale;
    private final ColorScheme colorScheme;
    private final Map<String,ViewRule> rules;

    public int getOffX(){
        return this.offX;
    }
    public void setOffX(int pOX){
        this.offX = pOX;
    }
    public int getOffY(){
        return this.offY;
    }
    public void setOffY(int pOY){
        this.offY = pOY;
    }
    public double getScale(){
        return this.scale;
    }
    public void setScale(double pScale) throws ScaleOutOfBoundsException{
        if(pScale<MIN_SCALE){
            throw new ScaleOutOfBoundsException("Scale "+pScale+" has to be at least "+MIN_SCALE);

        }
        if(pScale>MAX_SCALE)throw new ScaleOutOfBoundsException("Scale "+pScale+" has to be at most "+MAX_SCALE);
        this.scale = pScale;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public ViewRule getViewRule(String pKey){
        return rules.get(pKey);
    }
    public void setViewRule(String pKey, ViewRule pViewRule)throws KeyExistsException{
        if(this.rules.containsKey(pKey))throw new KeyExistsException("Key "+pKey+" already taken in ViewRules");
        this.rules.put(pKey, pViewRule);
    }

    public ViewState(ColorScheme pColorScheme){

        this.offX = 0;
        this.offY = 0;
        this.scale = 50d;//todo define default zoom

        this.rules = new TreeMap<String, ViewRule>();

        this.colorScheme = Objects.requireNonNullElseGet(pColorScheme, ColorScheme::getDefault);
    }

    public ViewState(){
        this(null);
    }


}
