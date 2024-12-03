package statics.app.view.components;


import statics.app.model.edges.Bar;
import statics.app.model.edges.Edge;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.model.nodes.Node;
import statics.app.view.components.edges.BarComponent;
import statics.app.view.components.nodes.JointComponent;
import statics.app.view.ViewState;

public class ComponentDispatcher {
    public static IComponent get(ViewState pVs, Object pO) throws NoConversionToComponentException {
        IComponent ret = null;
        if(pO instanceof IComponent c){
            ret = c;//todo change viewState
        }
        if(pO instanceof Node n){
            ret = nodeComponentDispatch(pVs,n);
        }
        if(pO instanceof IEdge e){
            ret = edgeComponentDispatch(pVs,e);
        }
        if (ret == null) {
            throw new NoConversionToComponentException(pO+" could not be converted to a component");
        }else{
            return ret;
        }
    }

    private static IComponent edgeComponentDispatch(ViewState pVs, IEdge e) {
        if(e instanceof Bar b){
            return new BarComponent(pVs,b);
        }
        return null;
    }

    private static IComponent nodeComponentDispatch(ViewState pVs, INode n) {
        if(n instanceof statics.app.model.nodes.Joint j){
            return new JointComponent(pVs,j);
        }
        return null;
    }
}
