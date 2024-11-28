package statics.app;

//todo comment

import statics.app.model.IConstruction;
import statics.app.view.ColorScheme;
import statics.app.view.IView;
import statics.app.view.ViewState;
import statics.json.IJsonObject;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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

    public App(IJsonObject pAdditionalConfig){
        //ensure integrity of fileConfig (//hardcode defaults and schema somewhere)
        //config = fileConfig + pAdditionalConfig

        ResourceBundle language = null;
        //get selected lang from config
        //if no or invalid lang selected, select System default
        //if no or invalid lang selected, select en_us
        //if no or invalid lang selected, select null

        ColorScheme colorScheme = null;
        //get selected colorScheme from config
        //if invalid colorScheme selected, select null

        //model = create Model(config path)
        //create ViewState(config colorScheme, pos, scale, ViewRules, lang)
        ViewState viewState = new ViewState(colorScheme, language);
        IActionHandler actionHandler = new ActionHandler(this, viewState);
        createView(viewState, actionHandler);
    }

    public void createView(ViewState pViewState, IActionHandler pActionHandler){
        this.view = new StaticsWindow(pViewState, pActionHandler);
        //todo recallable to reconstruct
    }
}
