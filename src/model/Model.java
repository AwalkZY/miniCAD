package model;

import controller.Controller;
import model.shape.Shape;
import view.View;

import java.awt.*;
import java.util.ArrayList;

public class Model {
    private Controller ctrl;
    private View view;
    private ArrayList<Shape> shapes;
    private int toolType = -1;
    private Color color = Color.WHITE;

    public void bindController(Controller ctrl){
        this.ctrl = ctrl;
    }

    public void bindView(View view){
        this.view = view;
    }

    public void setToolType(int newType){
        this.toolType = newType;
    }

    public int getToolType() {
        return this.toolType;
    }

    public void setColor(Color newColor){
        this.color = newColor;
    }

    public Color getColor(){
        return this.color;
    }

}
