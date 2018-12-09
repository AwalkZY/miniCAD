package view.panel;

import controller.Controller;
import view.button.ToolButton;
import model.common.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolPanel extends JPanel{
    private static final long serialVersionUID = 1L; //设置版本UID信息

    private ArrayList<ToolButton> toolButtons = new ArrayList<>();
    private ColorPanel colorPanel = new ColorPanel();

    public ToolPanel(){
        GridLayout mainLayout = new GridLayout(0,1);
        setLayout(mainLayout);
        initTools();
        add(colorPanel);
    }

    private void initTools() {
        toolButtons.add(new ToolButton("Pointer","static/icon/hand.png", Tool.POINTER));
        toolButtons.add(new ToolButton("Line","static/icon/line.png", Tool.LINE));
        toolButtons.add(new ToolButton("Fold Line","static/icon/fold.png", Tool.FOLD));
        toolButtons.add(new ToolButton("Polygon","static/icon/poly.png", Tool.POLY));
        toolButtons.add(new ToolButton("Rectangle","static/icon/rect.png", Tool.RECT));
        toolButtons.add(new ToolButton("Eclipse","static/icon/eclipse.png", Tool.ECLIPSE));
        toolButtons.add(new ToolButton("Text","static/icon/text.png", Tool.TEXT));
        for (ToolButton toolButton : toolButtons) {
            add(toolButton);
        }
    }

    public void bindController(Controller ctrl) {
        for (ToolButton toolButton : toolButtons) {
            toolButton.bindController(ctrl);
        }
        colorPanel.bindController(ctrl);
    }
}
