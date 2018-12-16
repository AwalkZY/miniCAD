package model.shape;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable, Cloneable {
    protected boolean selected;
    Color contourColor;
    Color insideColor;
    boolean isFilled;
    float strokeNum = 1.0f;

    public abstract void draw(Graphics2D g);

    public abstract void expand(Point p, boolean equal);

    public abstract void modify(Point p, boolean equal);

    public abstract boolean rollback();

    public void setContourColor(Color color) {
        contourColor = color;
    }

    public void setInsideColor(Color c) {
        insideColor = c;
    }

    public boolean getFilled() {
        return isFilled;
    }

    public void setFilled(boolean option) {
        isFilled = option;
    }

    public void setStroke(float strokeNum) {
        this.strokeNum = strokeNum;
    }

    public abstract void modifyText(String Text);

    public abstract boolean isClicked(Point p);

    public abstract void move(int delta_x, int delta_y);

    public abstract void drawBorder(Graphics2D g);

    public abstract void zoomShape(int scale);

    public Shape clone() throws CloneNotSupportedException {
        return (Shape) super.clone();
    }
}
