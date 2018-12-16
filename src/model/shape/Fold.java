package model.shape;

import model.common.Common;

import java.awt.*;
import java.util.Stack;

public class Fold extends Shape {
    private Stack<Integer> xPoint;
    private Stack<Integer> yPoint;

    public Fold(Point sp) {
        xPoint = new Stack<>();
        yPoint = new Stack<>();
        xPoint.push(sp.x);
        yPoint.push(sp.y);
        xPoint.push(sp.x);
        yPoint.push(sp.y);
    }

    @SuppressWarnings("unchecked")
    public Fold clone() throws CloneNotSupportedException {
        Fold newFold = (Fold) super.clone();
        newFold.xPoint = (Stack<Integer>) xPoint.clone();
        newFold.yPoint = (Stack<Integer>) yPoint.clone();
        return newFold;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(contourColor);
        g.setStroke(new BasicStroke(strokeNum));
        Integer[] xs = new Integer[xPoint.size()];
        Integer[] ys = new Integer[yPoint.size()];
        xPoint.toArray(xs);
        yPoint.toArray(ys);
        for (int i = 0; i < xPoint.size() - 1; i++) {
            g.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);
        }
    }

    @Override
    public void expand(Point p, boolean equal) {
        xPoint.push(p.x);
        yPoint.push(p.y);
        //System.out.println("size:" + xPoint.size());
    }

    @Override
    public void modify(Point p, boolean equal) {
        rollback();
        expand(p, false);
    }

    @Override
    public boolean rollback() {
        if (xPoint.size() >= 1) {
            xPoint.pop();
            yPoint.pop();
            return true;
        }
        return false;
    }

    @Override
    public void modifyText(String Text) {

    }

    @Override
    public boolean isClicked(Point p) {
        Integer[] xs = new Integer[xPoint.size()];
        Integer[] ys = new Integer[yPoint.size()];
        xPoint.toArray(xs);
        yPoint.toArray(ys);
        for (int i = 0; i < xPoint.size() - 1; i++) {
            if (Common.isNearby(new Point(xs[i], ys[i]), new Point(xs[i + 1], ys[i + 1]), p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(int delta_x, int delta_y) {
        Common.complexMove(delta_x, delta_y, xPoint, yPoint);
    }

    @Override
    public void drawBorder(Graphics2D g) {
        Common.complexDrawBorder(g, xPoint, yPoint);
    }

    @Override
    public void zoomShape(int scale) {
        Common.complexZoom(scale, xPoint, yPoint);
    }


}
