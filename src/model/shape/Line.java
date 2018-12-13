package model.shape;

import model.common.Common;

import java.awt.*;

public class Line extends Shape{
    private Point sp, ep;

    public Line(Point point){
        this.sp = point;
        this.ep = point;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(contourColor);
        g.drawLine(sp.x,sp.y,ep.x,ep.y);
    }

    @Override
    public void expand(Point p) {
        this.ep = p;
    }

    @Override
    public void modify(Point p) {
        this.ep = p;
    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public boolean isClicked(Point p) {
        return Common.isNearby(sp,ep,p);
    }

    @Override
    public void move(int delta_x, int delta_y) {
        sp.setLocation(sp.x+delta_x, sp.y+delta_y);
        ep.setLocation(ep.x+delta_x, ep.y+delta_y);
    }

    @Override
    public void drawBorder(Graphics2D g) {
        Common.drawBorder(g,Math.min(sp.x, ep.x),Math.min(sp.y, ep.y),sp.x + ep.x - 2 * Math.min(sp.x, ep.x),sp.y + ep.y - 2 * Math.min(sp.y, ep.y));
    }
}
