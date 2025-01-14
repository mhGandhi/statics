package statics.app;

//todo comment

import statics.app.model.Construction;
import statics.app.model.IConstruction;
import statics.app.model.SystemPos;
import statics.app.model.edges.Bar;
import statics.app.model.nodes.Joint;
import statics.app.view.*;
import statics.json.IJsonObject;

import java.awt.*;
import java.util.Collection;
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
        //IJsonObject config = Defaults.getConfig().add([FILE_CONFIG]).add(pAdditionalConfig);

        ResourceBundle language = null;
        //get selected lang from config
        //if no or invalid lang selected, select System default
        //if no or invalid lang selected, select en_us
        //if no or invalid lang selected, select null

        ColorScheme colorScheme = null;
        //get selected colorScheme from config
        //if no or invalid colorScheme selected, select System default
        //if invalid colorScheme selected, select null

        //model = create Model(config path)
        construction = new Construction();
        Joint j1 = new Joint(new SystemPos(3.2,1.9),DegreesOfFreedom.rotate());
        j1.setSupport(true);
        Joint j2 = new Joint(new SystemPos(4.93,1.1),DegreesOfFreedom.xr());
        j2.setSupport(true);
        Joint j3 = new Joint(new SystemPos(6.2,1.9),DegreesOfFreedom.rotate());
        construction.addNode(j1);
        construction.addNode(j2);
        construction.addNode(j3);
        construction.addEdge(new Bar(j1,j2), j1.getId(), j2.getId());//todo bar besser
        construction.addEdge(new Bar(j2,j3), j2.getId(), j3.getId());

        //create ViewState(config colorScheme, pos, scale, ViewRules, lang)
        ViewState viewState = new ViewState(colorScheme, language);
        IActionHandler actionHandler = new ActionHandler(this, viewState);
        createView(viewState, actionHandler);
        transferNodes();
    }

    public void createView(ViewState pViewState, IActionHandler pActionHandler){
        if(this.view != null){
            this.view.close();
        }
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


    public Collection<Integer> getComponentsAt(ScreenPos screenPos) {
        return view.getComponentsAt(screenPos);
    }
    public Collection<Integer> getComponentsAt(Rectangle pRect){
        return view.getComponentsAt(pRect);
    }

    public boolean isNodeComponent(int compI) {
        return view.isNodeComponent(compI);
    }

    public SystemPos getModelJointPosition(int nodeId){
        return construction.getJoint(nodeId).getPos();
    }

    public void setModelJointPostion(int nodeId, SystemPos pPos){
        construction.setJointPosition(nodeId, pPos);
    }

    public void setModelValidJointPostion(int nodeId, SystemPos pPos){
        construction.setJointPosition(nodeId, construction.validate(nodeId, pPos));
    }

    public void exit() {
        view.close();
        System.exit(0);

        System.err.println("Error while exiting");
    }
}
