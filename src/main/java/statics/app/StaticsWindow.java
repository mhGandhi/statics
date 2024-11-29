package statics.app;

import statics.app.view.IView;
import statics.app.view.RedrawModes;
import statics.app.view.StaticsPanel;
import statics.app.view.ViewState;

import javax.swing.*;
import java.awt.*;

//todo comment
public class StaticsWindow extends JFrame implements IView {
    private ViewState vs;
    private StaticsPanel panel;

    public StaticsWindow(ViewState pViewState, IActionHandler pActionHandler) {
        this.vs = pViewState;
        {
            this.setSize(500,500);//todo settings
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
        }

        this.panel = new StaticsPanel(pViewState, pActionHandler);

        this.add(this.panel,BorderLayout.CENTER);


        this.setVisible(true);
    }

    @Override
    public void repaint(RedrawModes pRedrawMode) {
        panel.repaint(pRedrawMode);
    }
}
