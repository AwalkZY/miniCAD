package view.button;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton {
    private Color color;

    public ColorButton(Color color){
        this.color = color;
        setBackground(color);
    }

    public void bindController(Controller ctrl) {
        addActionListener(ctrl.createColorListener(color));
    }
}
