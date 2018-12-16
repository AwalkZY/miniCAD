package model.shape;

import model.common.Common;

import java.awt.*;

public class Rectangle extends Shape {
    private Point sp, ep;

    public Rectangle(Point point) {
        this.sp = point;
        this.ep = point;
    }

    public Rectangle clone() throws CloneNotSupportedException {
        Rectangle newRectangle = (Rectangle) super.clone();
        newRectangle.sp = (Point) sp.clone();
        newRectangle.ep = (Point) ep.clone();
        return newRectangle;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(contourColor);
        g.setStroke(new BasicStroke(strokeNum));
        int x = Math.min(sp.x, ep.x);
        int y = Math.min(sp.y, ep.y);
        int width = sp.x + ep.x - 2 * x;
        int height = sp.y + ep.y - 2 * y;
        g.drawRect(x, y, width, height);
        g.setColor(insideColor);
        if (this.isFilled) g.fillRect(x + 1, y + 1, width - 1, height - 1);
    }

    @Override
    public void expand(Point p, boolean equal) {
        if (!equal) this.ep = p;
        else {
            int minDist = Math.min(p.x - sp.x, p.y - sp.y);
            this.ep = new Point(sp.x + minDist, sp.y + minDist);
        }
    }

    @Override
    public void modify(Point p, boolean equal) {
        if (!equal) this.ep = p;
        else {
            int minDist = Math.min(p.x - sp.x, p.y - sp.y);
            this.ep = new Point(sp.x + minDist, sp.y + minDist);
        }
    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public void modifyText(String Text) {

    }

    @Override
    public boolean isClicked(Point p) {
        return Common.simpleClicked(p, sp, ep);
    }

    @Override
    public void move(int delta_x, int delta_y) {
        sp.setLocation(sp.x + delta_x, sp.y + delta_y);
        ep.setLocation(ep.x + delta_x, ep.y + delta_y);
    }

    @Override
    public void drawBorder(Graphics2D g) {
        Common.drawBorder(g, Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), sp.x + ep.x - 2 * Math.min(sp.x, ep.x), sp.y + ep.y - 2 * Math.min(sp.y, ep.y));
    }

    @Override
    public void zoomShape(int scale) {
        Point[] zoomAns = Common.simpleZoom(scale, sp, ep);
        sp = zoomAns[0];
        ep = zoomAns[1];
    }
}
