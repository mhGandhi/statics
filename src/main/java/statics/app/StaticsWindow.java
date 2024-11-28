package statics.app;

import statics.app.view.IView;
import statics.app.view.ViewState;

import javax.swing.*;

public class StaticsWindow extends JFrame implements IView {
    public StaticsWindow(ViewState pViewState, IActionHandler pActionHandler) {
        this.setSize(500,500);//todo settings


        this.setVisible(true);
    }
}
