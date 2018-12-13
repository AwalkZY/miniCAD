package model.shape;

import model.common.Common;

import java.awt.*;
import java.util.Stack;

public class Fold extends Shape{
    private Stack<Integer> xPoint;
    private Stack<Integer> yPoint;

    public Fold(Point sp){
        xPoint = new Stack<>();
        yPoint = new Stack<>();
        xPoint.push(sp.x);
        yPoint.push(sp.y);
        xPoint.push(sp.x);
        yPoint.push(sp.y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(contourColor);
        Integer[] xs = new Integer[xPoint.size()];
        Integer[] ys = new Integer[yPoint.size()];
        xPoint.toArray(xs);
        yPoint.toArray(ys);
        for (int i = 0; i < xPoint.size()-1; i++) {
            g.drawLine(xs[i],ys[i],xs[i+1],ys[i+1]);
        }
    }

    @Override
    public void expand(Point p) {
        xPoint.push(p.x);
        yPoint.push(p.y);
        System.out.println("size:"+xPoint.size());
    }

    @Override
    public void modify(Point p) {
        rollback();
        expand(p);
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
    public boolean isClicked(Point p) {
        Integer[] xs = new Integer[xPoint.size()];
        Integer[] ys = new Integer[yPoint.size()];
        xPoint.toArray(xs);
        yPoint.toArray(ys);
        for (int i = 0; i < xPoint.size()-1; i++){
            if (Common.isNearby(new Point(xs[i],ys[i]),new Point(xs[i+1],ys[i+1]),p)) {
                return true;
            };
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


}
