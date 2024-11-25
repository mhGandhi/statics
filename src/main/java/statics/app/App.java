package statics.app;

//todo comment

import statics.app.model.IConstruction;
import statics.app.view.IView;

import java.util.List;

/**
 * controller for the application
 *
 * @author Adrian Akipi
 */
public class App {
    IConstruction construction;
    IView view;

    public App(){
        this(null);
    }

    public App(List<Integer> t/*list temp pConfig(fraction of config))*/){
        //ensure integrity of fileConfig (//hardcode defaults and schema somewhere)

        //if no or invalid lang selected, select System default
        //if no or invalid lang selected, select en_us
        //if no or invalid lang selected, select null


        //config = fileConfig + pConfig
        //model = create Model(config path)
        //create ViewState(config colorScheme, pos, scale, ViewRules, lang)
        //create ActionHandler(App, ViewState)
        //view = createView(ViewState, ActionHandler)
    }

    //createView(ViewState, ActionHandler){
    //#can be called again to reconstruct
    //womp womp
    //}
}
