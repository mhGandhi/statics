package statics.app.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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

        {
            JMenu m = newMenu("menu.view");

            m.add(newActionItem("action.toggle_bounds", Actions.TOGGLE+"drawBounds"));

            this.add(m);
        }
    }

    private JMenu newMenu(String pDisplay){
        JMenu jm = new JMenu(vs.t(pDisplay));
        jm.setBackground((Color)vs.getColorScheme().menu);
        jm.setForeground((Color)vs.getColorScheme().drawMenu);
        jm.getPopupMenu().setBorder(new LineBorder((Color)vs.getColorScheme().drawMenu));
        jm.getPopupMenu().setBackground((Color)vs.getColorScheme().menu);

        return jm;
    }

    private JMenuItem newActionItem(String pDisplay, String pAction){
        JMenuItem jmi = new JMenuItem(vs.t(pDisplay));
        jmi.setBackground((Color)vs.getColorScheme().menu);
        jmi.setForeground((Color)vs.getColorScheme().drawMenu);


        jmi.setActionCommand(pAction);
        jmi.addActionListener(actionListener);

        return jmi;
    }

    private JSeparator newSeparator(){
        JSeparator js = new JSeparator();
        js.setBackground((Color)vs.getColorScheme().menu);
        js.setForeground((Color)vs.getColorScheme().drawMenu);

        return js;
    }

    public static void addTo(StaticsWindow staticsWindow, ActionListener pListener, ViewState pVs) {
        staticsWindow.setJMenuBar(new MenuBar(pListener, pVs));
        //staticsWindow.setUndecorated(true);
        //todo add mb for mac os
    }
}