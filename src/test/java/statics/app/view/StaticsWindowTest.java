package statics.app.view;

import org.junit.jupiter.api.Test;
import statics.app.ActionHandler;
import statics.app.App;
import statics.app.IActionHandler;

import static org.junit.jupiter.api.Assertions.*;

class StaticsWindowTest {
//todo womp womp
    static IView getView(){
            ViewState vs = ViewStateTest.getRandomViewstate();
            App app = new App();
            IActionHandler ac = new ActionHandler(app, vs);
            app.createView(vs,ac);
            return new StaticsWindow(vs,ac);
    }

    @Test
    void repaint() {

    }

    @Test
    void setNodes() {
    }

    @Test
    void setEdges() {
    }
}