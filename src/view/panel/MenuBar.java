package view.panel;

import controller.Controller;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenuItem readItem = new JMenuItem("读取文件");
        JMenuItem saveItem = new JMenuItem("保存数据");
        JMenuItem copyItem = new JMenuItem("复制图形");
        JMenuItem pasteItem = new JMenuItem("粘贴图形");
        JMenuItem upAllItem = new JMenuItem("置于顶层");
        JMenuItem downAllItem = new JMenuItem("置于底层");
        JMenuItem upItem = new JMenuItem("上移一层");
        JMenuItem downItem = new JMenuItem("下移一层");
        readItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        upAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        downAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        upItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        downItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        readItem.addActionListener(Controller.getCurCtrl().createReadListener());
        saveItem.addActionListener(Controller.getCurCtrl().createSaveListener());
        copyItem.addActionListener(Controller.getCurCtrl().createCopyListener());
        pasteItem.addActionListener(Controller.getCurCtrl().createPasteListener());
        upAllItem.addActionListener(Controller.getCurCtrl().createLayerListener(1,true));
        downAllItem.addActionListener(Controller.getCurCtrl().createLayerListener(-1,true));
        upItem.addActionListener(Controller.getCurCtrl().createLayerListener(1,false));
        downItem.addActionListener(Controller.getCurCtrl().createLayerListener(-1,false));
        fileMenu.add(readItem);
        fileMenu.add(saveItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(upAllItem);
        editMenu.add(downAllItem);
        editMenu.add(upItem);
        editMenu.add(downItem);
        add(fileMenu);
        add(editMenu);
    }
}
