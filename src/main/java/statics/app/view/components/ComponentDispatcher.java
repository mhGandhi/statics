package statics.app.view.components;


import statics.app.DegreesOfFreedom;
import statics.app.model.edges.Bar;
import statics.app.model.edges.IEdge;
import statics.app.model.nodes.INode;
import statics.app.model.nodes.Node;
import statics.app.view.components.edges.ThickBarComponent;
import statics.app.view.components.nodes.JointComponent;
import statics.app.view.ViewState;
import statics.app.view.components.nodes.RotaryJointComponent;
import statics.app.view.components.nodes.supports.RotaryHorizontalSupportComponent;
import statics.app.view.components.nodes.supports.RotarySupportComponent;

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
            return new ThickBarComponent(pVs,b);
        }
        return null;
    }

    private static IComponent nodeComponentDispatch(ViewState pVs, INode n) {
        if(n instanceof statics.app.model.nodes.Joint j){
            if(j.isSupport()){
                if(DegreesOfFreedom.equal(j.getDegreesOfFreedom(),DegreesOfFreedom.rotate())){
                    return new RotarySupportComponent(pVs, j);
                }
                if(DegreesOfFreedom.equal(j.getDegreesOfFreedom(),DegreesOfFreedom.xr())){
                    return new RotaryHorizontalSupportComponent(pVs, j);
                }
            }else{
                if (DegreesOfFreedom.equal(j.getDegreesOfFreedom(),DegreesOfFreedom.rotate())){
                    return new RotaryJointComponent(pVs, j);
                }
            }

            return new JointComponent(pVs,j);
        }
        return null;
    }
}
