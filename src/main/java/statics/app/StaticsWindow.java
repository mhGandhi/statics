package statics.app;

import statics.app.view.IView;
import statics.app.view.StaticsPanel;
import statics.app.view.ViewState;

import javax.swing.*;
import java.awt.*;

//todo comment
public class StaticsWindow extends JFrame implements IView {
    StaticsPanel panel;

    public StaticsWindow(ViewState pViewState, IActionHandler pActionHandler) {
        this.setSize(500,500);//todo settings
        this.setLayout(new BorderLayout());

        this.panel = new StaticsPanel(pViewState, pActionHandler);

        this.add(this.panel,BorderLayout.CENTER);
        this.setVisible(true);
    }
}
