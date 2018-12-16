package view.panel;

import controller.Controller;

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
        readItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        readItem.addActionListener(Controller.getCurCtrl().createReadListener());
        saveItem.addActionListener(Controller.getCurCtrl().createSaveListener());
        copyItem.addActionListener(Controller.getCurCtrl().createCopyListener());
        pasteItem.addActionListener(Controller.getCurCtrl().createPasteListener());
        pasteItem.addActionListener(Controller.getCurCtrl().createPasteListener());
        fileMenu.add(readItem);
        fileMenu.add(saveItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
//        editMenu.addSeparator();
        add(fileMenu);
        add(editMenu);
    }
}
