package model.shape;

import model.common.Common;

import java.awt.*;

public class Line extends Shape {
    private Point sp, ep;

    public Line(Point point) {
        this.sp = point;
        this.ep = point;
    }

    public Line clone() throws CloneNotSupportedException {
        Line newLine = (Line) super.clone();
        newLine.sp = (Point) sp.clone();
        newLine.ep = (Point) ep.clone();
        return newLine;
    }

    @Override
    public void draw(Graphics2D g) {
        System.out.println(strokeNum);
        g.setStroke(new BasicStroke(strokeNum));
        g.setColor(contourColor);
        g.drawLine(sp.x, sp.y, ep.x, ep.y);
    }

    @Override
    public void expand(Point p, boolean equal) {
        if (!equal) this.ep = p;
        else {
            int minDist = Math.min(Math.abs(p.x - sp.x), Math.abs(p.y - sp.y));
            this.ep = new Point((int) (sp.x + minDist*Math.signum(p.x - sp.x)), (int) (sp.y + minDist*Math.signum(p.y - sp.y)));
        }
    }

    @Override
    public void modify(Point p, boolean equal) {
        if (!equal) this.ep = p;
        else {
            int minDist = Math.min(Math.abs(p.x - sp.x), Math.abs(p.y - sp.y));
            this.ep = new Point((int) (sp.x + minDist*Math.signum(p.x - sp.x)), (int) (sp.y + minDist*Math.signum(p.y - sp.y)));
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
        return Common.isNearby(sp, ep, p);
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
