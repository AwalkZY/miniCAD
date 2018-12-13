package view.panel;

import com.sun.jmx.mbeanserver.JmxMBeanServer;
import controller.Controller;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {

    public MenuBar(){
        JMenu menu1 = new JMenu("文件");
        JMenuItem item1 = new JMenuItem();
        item1 = new JMenuItem("读取文件",new ImageIcon("src/picture/dm.png"));
        JMenuItem item2 = new JMenuItem("保存数据",new ImageIcon("src/picture/java.png"));
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        item1.addActionListener(Controller.getCurCtrl().createReadListener());
        item2.addActionListener(Controller.getCurCtrl().createSaveListener());
        menu1.add(item1);
//        menu1.addSeparator();
        menu1.add(item2);
        add(menu1);
    }
}
