package statics.app;

import statics.app.model.SystemPos;
import statics.app.view.RedrawModes;
import statics.app.view.ScaleOutOfBoundsException;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * handles all actions in the View
 *
 * @author Adrian Akipi
 *///todo comment
public class ActionHandler implements IActionHandler, MouseMotionListener, MouseListener, ComponentListener, KeyListener, MouseWheelListener {
    private App app;
    private ViewState vs;

    public ActionHandler(App pApp, ViewState pViewState) {
        this.app = pApp;
        this.vs = pViewState;
    }




    //private ScreenPos lastPress = new ScreenPos(0,0);
    private ScreenPos lastMousePos = new ScreenPos(0,0);

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int dragX = -e.getX()+lastMousePos.getX();
        int dragY = -e.getY()+lastMousePos.getY();

        if(selectedNodes.isEmpty()){
            vs.setOffX(vs.getOffX()+dragX);
            vs.setOffY(vs.getOffY()+dragY);

            this.app.repaintView(RedrawModes.MOVED);
        }else{

            for(int nodeId : selectedNodes){
                ScreenPos nodeScPos = vs.toScreenPos(app.getModelJointPosition(nodeId));
                ScreenPos newNodeScPos = new ScreenPos(nodeScPos.getX()-dragX,nodeScPos.getY()-dragY);
                app.setModelJointPostion(nodeId, vs.toSysPos(newNodeScPos));
            }
            app.transferNodes();
            app.repaintView(RedrawModes.MOVED);
        }
        lastMousePos.set(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        Collection<Integer> nodesAtPoint = app.getComponentsAt(new ScreenPos(e));
        List<Integer> hn;
        try{
            hn = (List<Integer>)vs.getViewRule("highlightedComponents").getValue();
        }catch (Exception ex){
            ex.printStackTrace();
            return;
        }
        hn.clear();
        hn.addAll(nodesAtPoint);
        //System.out.println(hn);
        app.repaintView(RedrawModes.UNMOVED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    List<Integer> selectedNodes = new ArrayList<>();
    boolean selectedNodeDirectly = true;
    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos.set(e);
        //lastPress.set(e);
        List<Integer> nodesAtPress = getNodesAt(new ScreenPos(e));
        if(selectedNodes.isEmpty() && !nodesAtPress.isEmpty()){
            selectedNodes = new ArrayList<>();
            selectedNodes.add(nodesAtPress.getFirst());
            selectedNodeDirectly = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(selectedNodeDirectly){
            selectedNodes = new ArrayList<>();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when the component's size changes.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {
        vs.setHeight(e.getComponent().getHeight());
        vs.setWidth(e.getComponent().getWidth());
    }

    /**
     * Invoked when the component's position changes.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentMoved(ComponentEvent e) {

    }

    /**
     * Invoked when the component has been made visible.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }

    /**
     * Invoked when the component has been made invisible.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case '+':
                try{
                    vs.setScale(vs.getScale()+5);
                }catch (ScaleOutOfBoundsException _){

                }
                app.repaintView(RedrawModes.RESCALE);
                break;
            case '-':
                try{
                    vs.setScale(vs.getScale()-5);
                }catch (ScaleOutOfBoundsException _){

                }
                app.repaintView(RedrawModes.RESCALE);
                break;
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    long lastMWETime = 0;
    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param e the event to be processed
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        long currentMWETime = System.nanoTime();
        long timeSinceLastMWE = currentMWETime- lastMWETime;//geschw 1step/tslm
        lastMWETime = currentMWETime;
        int scrollSpeedMod = Math.min(1+(int)Math.round(500/Math.max((((int)timeSinceLastMWE)),1.0)),10);//500 const




        double zoomInc = -0.1 * e.getWheelRotation()*Math.max(scrollSpeedMod/20.0,1.0);//20 const
        double zoomFac = 1 + zoomInc;
        double totalZoomFac = vs.getScale() * zoomFac;

        //wenn zoomwert out of bounds gehen würde, wird der andre kak direkt geskippt
        if ((vs.getScale()==10&&zoomFac<1)||(vs.getScale()>500&&zoomFac>1)){
            return;
        }

        //enden schön rund machen
        if (totalZoomFac > 500)totalZoomFac = 500;
        if (totalZoomFac < 10)totalZoomFac = 10;

        ScreenPos mousePos = new ScreenPos(e);


        SystemPos mousePosInSystemBeforeZoom = vs.toSysPos(mousePos);
        vs.setScale((int)Math.round(totalZoomFac));
        SystemPos mousePosInSystemAfterZoom = vs.toSysPos(mousePos);

        double offX = -mousePosInSystemAfterZoom.getX()+mousePosInSystemBeforeZoom.getX();
        double offY = -mousePosInSystemAfterZoom.getY()+mousePosInSystemBeforeZoom.getY();
        vs.setOffX((int)Math.round(vs.getOffX()+offX*vs.getScale()));
        vs.setOffY((int)Math.round(vs.getOffY()+offY*vs.getScale()));

        app.repaintView(RedrawModes.RESCALE);
        /*
        //zoom überarbeiten?
        if(keyMod_strg){
            double zoomInc = -0.1 * e.getWheelRotation()*Math.max(scrollSpeedMod/20,1);//20 const
            double zoomFac = 1 + zoomInc;
            double totalZoomFac = getViewState().getZoom() * zoomFac;


            //wenn zoomwert out of bounds gehen würde, wird der andre kak direkt geskippt
            if ((getViewState().getZoom()==10&&zoomFac<1)||(getViewState().getZoom()>500&&zoomFac>1)){
                return;
            }

            //enden schön rund machen
            if (totalZoomFac > 500)totalZoomFac = 500;
            if (totalZoomFac < 10)totalZoomFac = 10;

            //Dumme kacke arschritze 3h meines Lebens und doch nix geworden nichtmal Ki degah ich scheiß mir ein wär ich mal schlafen gegangen logischster kack überhaupt kann nicht verstehen warum das so lange gedauert hat ich schwöre ich nehme mir das Leben Amina Koyim
            ActualPoint ap = new ActualPoint(e.getX(), e.getY());

            InSysPoint mousePointInSystemBeforeZoom = getViewState().getPointInSystem(ap);
            getViewState().setZoom(totalZoomFac);
            InSysPoint mousePointInSystemAfterZoom = getViewState().getPointInSystem(ap);

            double offX = -mousePointInSystemAfterZoom.getX()+mousePointInSystemBeforeZoom.getX();
            double offY = -mousePointInSystemAfterZoom.getY()+mousePointInSystemBeforeZoom.getY();
            getViewState().setOffsetX((int)Math.round(getViewState().getOffsetX()+offX*getViewState().getZoom()));
            getViewState().setOffsetY((int)Math.round(getViewState().getOffsetY()+offY*getViewState().getZoom()));

            getActionHandler().redraw_Rescale();
        }else if(keyMod_shift) {
            getViewState().setOffsetX(getViewState().getOffsetX()+e.getWheelRotation()*scrollSpeedMod);
            getActionHandler().redraw_Move();
        }else{
            getViewState().setOffsetY(getViewState().getOffsetY()+e.getWheelRotation()*scrollSpeedMod);
            getActionHandler().redraw_Move();
        }
        */
    }

    private List<Integer> getNodesAt(ScreenPos pPos){
        Collection<Integer> comps = app.getComponentsAt(pPos);
        return extractNodeIds(comps);
    }

    private List<Integer> getNodesAt(Rectangle pRect){
        Collection<Integer> comps = app.getComponentsAt(pRect);
        return extractNodeIds(comps);
    }

    private List<Integer> extractNodeIds(Collection<Integer> pComps){
        List<Integer> ret = new ArrayList<>();
        for (int compI : pComps){
            if(app.isNodeComponent(compI)){
                ret.add(compI);
            }
        }
        return ret;
    }
}
