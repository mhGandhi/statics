package statics.app;

import statics.app.view.RedrawModes;
import statics.app.view.ScaleOutOfBoundsException;
import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.event.*;

/**
 * handles all actions in the View
 *
 * @author Adrian Akipi
 *///todo comment
public class ActionHandler implements IActionHandler, MouseMotionListener, MouseListener, ComponentListener, KeyListener {
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

        vs.setOffX(vs.getOffX()+dragX);
        vs.setOffY(vs.getOffY()+dragY);



        lastMousePos.set(e);
        this.app.repaintView(RedrawModes.MOVED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos.set(e);
        //lastPress.set(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {

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
}
