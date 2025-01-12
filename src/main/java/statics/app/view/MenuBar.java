package statics.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    public MenuBar(ActionListener pListener){
        super();

        JMenu jm = new JMenu(":nerd:");
        jm.add("hihi");
        this.add(jm);
    }

    public static void addTo(StaticsWindow staticsWindow, ActionListener pListener) {
        staticsWindow.setJMenuBar(new MenuBar(pListener));
        //staticsWindow.setUndecorated(true);
        //todo add mb for mac os
    }
}