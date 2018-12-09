package controller;

import model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private ArrayList<ActionListener> toolListeners = new ArrayList<>();
    private ArrayList<ActionListener> colorListeners = new ArrayList<>();

    public Controller(){}

    public void bindModel(Model model){
        this.model = model;
    }

    public ActionListener createToolListener(int typeCode){
        ActionListener toolListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setToolType(typeCode);
            }
        };
        toolListeners.add(toolListener);
        return toolListener;
    }

    public ActionListener createColorListener(Color color){
        ActionListener colorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setColor(color);
            }
        };
        colorListeners.add(colorListener);
        return colorListener;
    }
}
