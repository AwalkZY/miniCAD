package model.shape;

import model.common.Common;

import java.awt.*;
import java.util.Stack;

public class Polygon extends Shape {
    private Stack<Integer> xPoint;
    private Stack<Integer> yPoint;

    public Polygon(Point sp) {
        xPoint = new Stack<>();
        yPoint = new Stack<>();
        xPoint.push(sp.x);
        yPoint.push(sp.y);
        xPoint.push(sp.x);
        yPoint.push(sp.y);
    }

    @SuppressWarnings("unchecked")
    public Polygon clone() throws CloneNotSupportedException {
        Polygon newPolygon = (Polygon) super.clone();
        newPolygon.xPoint = (Stack<Integer>) xPoint.clone();
        newPolygon.yPoint = (Stack<Integer>) yPoint.clone();
        return newPolygon;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(contourColor);
        g.setStroke(new BasicStroke(strokeNum));
        Integer[] xs = new Integer[xPoint.size()];
        Integer[] ys = new Integer[yPoint.size()];
        xPoint.toArray(xs);
        yPoint.toArray(ys);
//        System.out.println("xPoint: " + xPoint.size());
//        System.out.println("yPoint: " + yPoint.size());
        int[] int_xs = new int[xPoint.size()];
        int[] int_ys = new int[yPoint.size()];
        for (int i = 0; i < xPoint.size(); i++) int_xs[i] = xs[i];
        for (int i = 0; i < yPoint.size(); i++) int_ys[i] = ys[i];
        g.drawPolygon(int_xs, int_ys, xPoint.size());
        g.setColor(insideColor);
        if (this.isFilled) g.fillPolygon(int_xs, int_ys, xPoint.size());
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
        int min_x = xs[0];
        int max_x = xs[0];
        int min_y = ys[0];
        int max_y = ys[0];
        for (Integer x : xs) {
            min_x = Math.min(min_x, x);
            max_x = Math.max(max_x, x);
        }
        for (Integer y : ys) {
            min_y = Math.min(min_y, y);
            max_y = Math.max(max_y, y);
        }
        return p.x <= max_x && p.x >= min_x && p.y >= min_y && p.y <= max_y;
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
