package statics.app;

//todo comment

import statics.app.model.Construction;
import statics.app.model.IConstruction;
import statics.app.model.SystemPos;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.model.nodes.Joint;
import statics.app.view.*;
import statics.json.IJsonObject;

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

        //add config from file
        //IJsonObject config = Defaults.getConfig().add(null).add(pAdditionalConfig);

        ResourceBundle language = null;
        //get selected lang from config
        //if no or invalid lang selected, select System default
        //if no or invalid lang selected, select en_us
        //if no or invalid lang selected, select null

        ColorScheme colorScheme = null;
        //get selected colorScheme from config
        //if invalid colorScheme selected, select null

        //model = create Model(config path)
        construction = new Construction();
        construction.addNode(new Joint(new SystemPos(3,2),2));

        //create ViewState(config colorScheme, pos, scale, ViewRules, lang)
        ViewState viewState = new ViewState(colorScheme, language);
        IActionHandler actionHandler = new ActionHandler(this, viewState);
        createView(viewState, actionHandler);
        transferNodes();
    }

    public void createView(ViewState pViewState, IActionHandler pActionHandler){
        this.view = new StaticsWindow(pViewState, pActionHandler);
        //todo recallable to reconstruct
    }

    public void repaintView(RedrawModes pRedrawMode) {
        this.view.repaint(pRedrawMode);
    }

    public void transferNodes(){
        view.setNodes(construction.getNodes());
        transferEdges();
    }

    public void transferEdges(){
        view.setEdges(construction.getEdges());
    }
}
