package statics.app;

import statics.app.view.ScreenPos;
import statics.app.view.ViewState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * handles all actions in the View
 *
 * @author Adrian Akipi
 *///todo comment
public class ActionHandler implements IActionHandler, MouseMotionListener, MouseListener {
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
        this.app.repaintView();
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
}
