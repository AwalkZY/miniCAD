package view.panel;

import controller.Controller;
import view.button.ColorButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ColorPanel extends JPanel {
    private static final long serialVersionUID = 1L; //设置版本UID信息

    private ArrayList<ColorButton> colorButtons = new ArrayList<ColorButton>();

    ColorPanel(){
        GridLayout mainLayout = new GridLayout(3,3,1,1);
        setLayout(mainLayout);
        initColors();
    }

    private void initColors() {
        colorButtons.add(new ColorButton(Color.WHITE));
        colorButtons.add(new ColorButton(Color.GRAY));
        colorButtons.add(new ColorButton(Color.BLACK));
        colorButtons.add(new ColorButton(Color.BLUE));
        colorButtons.add(new ColorButton(Color.RED));
        colorButtons.add(new ColorButton(Color.GREEN));
        colorButtons.add(new ColorButton(Color.YELLOW));
        colorButtons.add(new ColorButton(Color.MAGENTA));
        colorButtons.add(new ColorButton(Color.ORANGE));

        for (ColorButton colorButton : colorButtons) {
            add(colorButton);
        }
    }

    void bindController(Controller ctrl) {
        for (ColorButton colorButton : colorButtons) {
            colorButton.bindController(ctrl);
        }
    }
}
