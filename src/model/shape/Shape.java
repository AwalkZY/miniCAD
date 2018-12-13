package model.shape;

import controller.state.State;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    Color contourColor;
    Color insideColor;
    boolean isFilled;
    protected boolean selected;
    public abstract void draw(Graphics2D g);
    public abstract void expand(Point p);
    public abstract void modify(Point p);
    public abstract boolean rollback();
    public void setContourColor(Color color){
        contourColor = color;
    }
    public void setInsideColor(Color c){
        insideColor = c;
    }
    public void setFilled(boolean option){
        isFilled = option;
    }
    public boolean getFilled(){
        return isFilled;
    }
    public abstract boolean isClicked(Point p);
    public abstract void move(int delta_x, int delta_y);
    public abstract void drawBorder(Graphics2D g);
}
