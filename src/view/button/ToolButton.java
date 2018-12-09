package view.button;

import controller.Controller;

import javax.swing.*;

public class ToolButton extends JButton{
    private int typeCode;

    public ToolButton(String description, String iconPath, int typeCode){
        this.typeCode = typeCode;
        ImageIcon imgIcon = new ImageIcon(iconPath);
        setVerticalTextPosition(SwingConstants.BOTTOM);  //垂直方向把文本放到底部
        setHorizontalTextPosition(SwingConstants.CENTER);  //水平方向把文本放到中部
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(50, 50, 1));  //放缩到(50,50)
        setIcon(imgIcon);
        setText(description);
    }

    public void bindController(Controller ctrl){
        addActionListener(ctrl.createToolListener(typeCode));
    }
}
