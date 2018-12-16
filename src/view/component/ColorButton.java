package view.component;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton {

    public ColorButton(Color color) {
        setBackground(color);
        addMouseListener(Controller.getCurCtrl().createColorListener(color));
    }
}
