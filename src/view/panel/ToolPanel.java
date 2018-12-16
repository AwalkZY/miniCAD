package view.panel;

import controller.Controller;
import model.common.Tool;
import view.component.ToolButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolPanel extends JPanel {
    private static final long serialVersionUID = 1L; //设置版本UID信息

    private ArrayList<ToolButton> toolButtons = new ArrayList<>();
    private ColorPanel colorPanel = new ColorPanel();

    private JToggleButton fillToggle;

    public ToolPanel() {
        GridLayout mainLayout = new GridLayout(0, 2);
        setLayout(mainLayout);
        initToggles();
        initTools();
        add(colorPanel);
    }

    private void initToggles() {
        ImageIcon imgIcon = new ImageIcon("static/icon/color.png");
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(50, 50, 1));
        fillToggle = new JToggleButton("Fill-in", imgIcon);
        fillToggle.addChangeListener(Controller.getCurCtrl().createChangeListener());
        add(fillToggle);
    }

    private void initTools() {
        toolButtons.add(new ToolButton("Pointer", "static/icon/hand.png", Tool.POINTER));
        toolButtons.add(new ToolButton("Line", "static/icon/line.png", Tool.LINE));
        toolButtons.add(new ToolButton("Fold Line", "static/icon/fold.png", Tool.FOLD));
        toolButtons.add(new ToolButton("Polygon", "static/icon/poly.png", Tool.POLY));
        toolButtons.add(new ToolButton("Rectangle", "static/icon/rect.png", Tool.RECT));
        toolButtons.add(new ToolButton("Ellipse", "static/icon/ellipse.png", Tool.ELLIPSE));
        toolButtons.add(new ToolButton("Text", "static/icon/text.png", Tool.TEXT));
        toolButtons.add(new ToolButton("Clear", "static/icon/trash.png", Tool.TRASH));
        for (ToolButton toolButton : toolButtons) {
            add(toolButton);
        }
    }
}
