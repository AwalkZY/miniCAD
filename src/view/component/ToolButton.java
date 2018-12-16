package view.component;

import controller.Controller;

import javax.swing.*;

public class ToolButton extends JButton {

    public ToolButton(String description, String iconPath, int typeCode) {
        ImageIcon imgIcon = new ImageIcon(iconPath);
        setVerticalTextPosition(SwingConstants.BOTTOM);  //垂直方向把文本放到底部
        setHorizontalTextPosition(SwingConstants.CENTER);  //水平方向把文本放到中部
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(50, 50, 1));  //放缩
        setIcon(imgIcon);
        setText(description);
        addActionListener(Controller.getCurCtrl().createToolListener(typeCode));
    }
}
