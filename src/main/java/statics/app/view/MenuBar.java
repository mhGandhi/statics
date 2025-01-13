package statics.app.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private final ActionListener actionListener;
    private final ViewState vs;

    public MenuBar(ActionListener pListener, ViewState pVs){
        super();
        this.actionListener = pListener;
        this.vs = pVs;

        {//file menu
            JMenu m = newMenu("menu.file");
            
            m.add(newActionItem("action.new_file", Actions.NEW_FILE));
            m.add(newSeparator());
            m.add(newActionItem("action.save", Actions.SAVE));
            m.add(newActionItem("action.save_as", Actions.SAVE_AS));
            m.add(newActionItem("action.load", Actions.LOAD));

            this.add(m);
        }
    }

    private JMenu newMenu(String pDisplay){
        return new JMenu(vs.t(pDisplay));
    }

    private JMenuItem newActionItem(String pDisplay, String pAction){
        JMenuItem jmi = new JMenuItem(vs.t(pDisplay));

        jmi.setActionCommand(pAction);
        jmi.addActionListener(actionListener);

        return jmi;
    }

    private JSeparator newSeparator(){
        JSeparator js = new JSeparator();
        //js.setForeground();
        //js.setBackground();

        return js;
    }

    public static void addTo(StaticsWindow staticsWindow, ActionListener pListener, ViewState pVs) {
        staticsWindow.setJMenuBar(new MenuBar(pListener, pVs));
        //staticsWindow.setUndecorated(true);
        //todo add mb for mac os
    }
}