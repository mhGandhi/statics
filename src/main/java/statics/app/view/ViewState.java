package statics.app.view;

import statics.app.model.SystemPos;
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
    private int scale;
    private final ColorScheme colorScheme;
    private final Map<String,ViewRule<?>> rules;

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
    public int getScale(){
        return this.scale;
    }
    public void setScale(int pScale) throws ScaleOutOfBoundsException{
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
        this.scale = 50;//todo define default zoom

        this.rules = new TreeMap<String, ViewRule<?>>();

        this.colorScheme = Objects.requireNonNullElseGet(pColorScheme, ColorScheme::getDefault);
    }

    public ViewState(){
        this(null);
    }

    public ScreenPos toScreenPos(SystemPos pSystemPos){
        if(pSystemPos==null)throw new NullPointerException();

        int x = (int)Math.round((pSystemPos.getX())*getScale()-getOffX());
        int y = (int)Math.round((pSystemPos.getY())*getScale()-getOffY());

        //int x = (int)Math.round(pSystemPos.getX()*getScale());
        //int y = (int)Math.round(pSystemPos.getY()*getScale());

        return new ScreenPos(x,y);
    }

    public SystemPos toSysPos(ScreenPos pScreenPos){
        if(pScreenPos==null)throw new NullPointerException();

        double x = ((double)(pScreenPos.getX()+getOffX()))/getScale();
        double y = ((double)(pScreenPos.getY()+getOffY()))/getScale();

        //double x = (double) pScreenPos.getX() /getScale();
        //double y = (double) pScreenPos.getY() /getScale();

        return new SystemPos(x,y);
    }
}
