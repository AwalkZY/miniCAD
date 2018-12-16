package model;

import controller.Controller;
import model.common.Tool;
import model.shape.*;
import model.shape.Polygon;
import model.shape.Rectangle;
import model.shape.Shape;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private static Model curModel;
    private Controller ctrl;
    private View view;
    private boolean equal = false;
    private int toolType = Tool.POINTER;
    private Color color = Color.BLACK;
    private boolean isFilled = false;
    private Color insideColor = Color.BLACK;
    private Shape curShape;
    private Shape copyShape;
    private HashMap<Integer, Class> toolClass = new HashMap<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Shape> garbage = new ArrayList<>();
    private float strokeVal = 1.0f;

    public Model() {
        curModel = this;
        toolClass.put(Tool.LINE, Line.class);
        toolClass.put(Tool.FOLD, Fold.class);
        toolClass.put(Tool.ELLIPSE, Ellipse.class);
        toolClass.put(Tool.TEXT, Text.class);
        toolClass.put(Tool.RECT, Rectangle.class);
        toolClass.put(Tool.POLY, Polygon.class);
    }

    static public Model getCurModel() {
        return curModel;
    }

    public int getToolType() {
        return this.toolType;
    }

    public void setToolType(int newType) {
        this.toolType = newType;
    }

    public void setColor(Color newColor, int option) {
        if (option == 0) {
            this.color = newColor;
            if (curShape != null) {
                curShape.setContourColor(newColor);
                view.reRender();
            }
            return;
        }
        this.insideColor = newColor;
        if (curShape != null) {
            curShape.setInsideColor(newColor);
            curShape.setFilled(true);
            view.reRender();
        }
    }

    public Color getColor() {
        return this.color;
    }

    public void expandShape(Point p, boolean equal) {
        if (curShape == null) return;
        curShape.expand(p, equal);
        view.reRender();
    }

    public void modifyShape(Point p, boolean equal) {
        if (curShape == null) return;
        curShape.modify(p, equal);
        view.reRender();
    }

    public boolean rollbackShape() {
        if (curShape == null) return false;
        boolean ans = true;
        if (!curShape.rollback()) {
            shapes.remove(curShape);
            ans = false;
        }
        view.reRender();
        return ans;  //ans : 是否仍然保持该图形
    }

    @SuppressWarnings("unchecked")
    public void initOperation(Point point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (toolType == Tool.POINTER) {
            curShape = null;
            for (int i = 0; i < shapes.size(); i++)
                if (shapes.get(i).isClicked(point)) curShape = shapes.get(i);
        } else {
            Class shapeClass = toolClass.get(toolType);  //寻找当前类
            Constructor shapeCtor = shapeClass.getConstructor(Point.class);  //获得当前类的单点创建函数
            curShape = (Shape) shapeCtor.newInstance(point);  //新建新的shape
            curShape.setContourColor(this.color);
            curShape.setInsideColor(this.insideColor);
            curShape.setFilled(this.isFilled);
            curShape.setStroke(strokeVal);
            shapes.add(curShape);
            if (toolType == Tool.TEXT) {
                Model.getCurModel().modifyText();
            }
        }
        view.reRender();
    }

    public void clear() {
        this.shapes.clear();
        view.reRender();
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    public void switchFilled() {
        isFilled = !isFilled;
    }

    public void saveToFile() {
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
            view.reRender();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void moveShape(int delta_x, int delta_y) {
        if (curShape == null) return;
        curShape.move(delta_x, delta_y);
        view.reRender();
    }

    public boolean clickCurShape(Point point) {
        return (curShape != null && curShape.isClicked(point));
    }

    public void removeShape() {
        if (curShape == null) return;
        shapes.remove(curShape);
        curShape = null;
        view.reRender();
    }

    public Shape getChosenShape() {
        if (toolType == Tool.POINTER) {
            return curShape;
        }
        return null;
    }

    public void modifyText() {
        if (!(curShape instanceof Text)) return;
        String text = JOptionPane.showInternalInputDialog(view.getMainFrame().getContentPane(), "请输入文本信息", "文本输入", JOptionPane.QUESTION_MESSAGE);
        curShape.modifyText(text);
        view.reRender();
    }

    public void zoomShape(int scale) {
        if (curShape == null) return;
        curShape.zoomShape(scale);
        view.reRender();
    }

    public void flushCurShape() {
        curShape = null;
    }

    public void bindController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public void bindView(View view) {
        this.view = view;
    }

    public void modifyStroke(int scale) {
        strokeVal = Math.max(1.0f, strokeVal + scale * 0.1f);
        if (curShape == null) return;
        curShape.setStroke(strokeVal);
        view.reRender();
    }

    public boolean getEqual() {
        return this.equal;
    }

    public void setEqual(boolean equal) {
        this.equal = equal;
        view.reRender();
    }

    public void changeFilled() {
        if (curShape == null) return;
        curShape.setFilled(!curShape.getFilled());
        view.reRender();
    }

    public void copy() {
        if (curShape == null) return;
        try {
            copyShape = curShape.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void paste() {
        if (copyShape == null) return;
        copyShape.move((int) (Math.random() * 200), (int) (Math.random() * 200));
        shapes.add(copyShape);
        view.reRender();
    }
}
