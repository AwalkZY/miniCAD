package model;

import controller.Controller;
import model.common.Tool;
import model.shape.*;
import model.shape.Polygon;
import model.shape.Rectangle;
import model.shape.Shape;
import view.View;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private Controller ctrl;
    private View view;
    private int toolType = Tool.POINTER;
    private Color color = Color.BLACK;
    private boolean isFilled = false;
    private Color insideColor = Color.BLACK;
    private static Model curModel;
    private Shape curShape;
    private HashMap<Integer, Class> toolClass = new HashMap<>();
    private ArrayList<Shape> shapes = new ArrayList<>();

    public Model(){
        curModel = this;
        toolClass.put(Tool.LINE, Line.class);
        toolClass.put(Tool.FOLD, Fold.class);
        toolClass.put(Tool.ELLIPSE, Ellipse.class);
        toolClass.put(Tool.TEXT, Text.class);
        toolClass.put(Tool.RECT, Rectangle.class);
        toolClass.put(Tool.POLY, Polygon.class);
    }

    static public Model getCurModel(){
        return curModel;
    }

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

    public void setColor(Color newColor, int option){
        if (option == 0) {
            this.color = newColor;
            if (toolType == Tool.POINTER && curShape != null) {
                curShape.setContourColor(newColor);
                View.getCurView().reRender();
            }
            return;
        }
        this.insideColor = newColor;
        if (toolType == Tool.POINTER && curShape != null) {
            curShape.setInsideColor(newColor);
            curShape.setFilled(true);
            View.getCurView().reRender();
        }
    }

    public Color getColor(){
        return this.color;
    }

    public void expandShape(Point p) {
        if (curShape != null) {
            curShape.expand(p);
            View.getCurView().reRender();
        }
    }

    public void modifyShape(Point p){
        if (curShape != null) {
            curShape.modify(p);
            View.getCurView().reRender();
        }
    }

    public boolean rollbackShape(){
        if (curShape == null) return false;
        boolean ans = true;
        if (!curShape.rollback()) {
            shapes.remove(curShape);
            ans = false;
        }
        View.getCurView().reRender();
        return ans;  //ans : 是否仍然保持该图形
    }

    @SuppressWarnings("unchecked")
    public void initOperation(Point point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (toolType == Tool.POINTER) {
            curShape = null;
            for (int i = 0; i < shapes.size(); i++)
                if (shapes.get(i).isClicked(point)) curShape = shapes.get(i);
        }
        else {
            Class shapeClass = toolClass.get(toolType);  //寻找当前类
            Constructor shapeCtor = shapeClass.getConstructor(Point.class);  //获得当前类的单点创建函数
            curShape = (Shape) shapeCtor.newInstance(point);  //新建新的shape
            curShape.setContourColor(this.color);
            curShape.setInsideColor(this.insideColor);
            curShape.setFilled(this.isFilled);
            shapes.add(curShape);
        }
        View.getCurView().reRender();
    }

    public void clear(){
        this.shapes.clear();
        View.getCurView().reRender();
    }

    public ArrayList<Shape> getShapes(){
        return this.shapes;
    }

    public void switchFilled() {
        isFilled = !isFilled;
        if (toolType == Tool.POINTER && curShape != null && isFilled != curShape.getFilled()) {
            curShape.setFilled(isFilled);
            View.getCurView().reRender();
        }
    }

    public void saveToFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                    new File("Shape.txt")));
            oos.writeObject(shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void readFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    new File("Shape.txt")));
            shapes = (ArrayList<Shape>) ois.readObject();
            View.getCurView().reRender();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void moveShape(int delta_x, int delta_y) {
        if (curShape != null) {
            curShape.move(delta_x, delta_y);
            View.getCurView().reRender();
        }
    }

    public boolean clickCurShape(Point point) {
        return (curShape != null && curShape.isClicked(point));
    }

    public void removeShape() {
        if (curShape == null) return;
        shapes.remove(curShape);
        curShape = null;
        View.getCurView().reRender();
    }

    public Shape getChosenShape() {
        if (toolType == Tool.POINTER) {
            return curShape;
        }
        return null;
    }
}
