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

        JMenu jm = new JMenu("nerd");
        jm.add("hihi");
        this.add(jm);
    }

    private JMenu newMenu(String pDisplay){
        return new JMenu(vs.t(pDisplay));
    }

    private JMenuItem newActionItem(String pDisplay, String pAction){
        JMenuItem jmi = new JMenuItem();

        jmi.setActionCommand(pAction);
        jmi.addActionListener(actionListener);

        jmi.setName(vs.t(pDisplay));

        return jmi;
    }

    public static void addTo(StaticsWindow staticsWindow, ActionListener pListener, ViewState pVs) {
        staticsWindow.setJMenuBar(new MenuBar(pListener, pVs));
        //staticsWindow.setUndecorated(true);
        //todo add mb for mac os
    }
}