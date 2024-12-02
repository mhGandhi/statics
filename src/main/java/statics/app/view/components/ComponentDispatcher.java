package statics.app.view.components;


import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.view.ViewState;

public class ComponentDispatcher {
    public IComponent get(ViewState pVs, Object pO) throws NoConversionToComponentException {
        if(pO instanceof IComponent c){
            return c;//todo change viewState
        }
        if(pO instanceof INode n){
            return nodeComponentDispatch(pVs,n);
        }
        if(pO instanceof IEdge e){
            return edgeComponentDispatch(pVs,e);
        }
        throw new NoConversionToComponentException(pO+" could not be converted to a component");
    }

    private IComponent edgeComponentDispatch(ViewState pVs, IEdge e) {
        return null;
    }

    private IComponent nodeComponentDispatch(ViewState pVs, INode n) {
    }
}
