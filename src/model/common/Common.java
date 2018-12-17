package model.common;

import java.awt.*;
import java.util.Stack;

public class Common {
    static final double ZOOMRATE = 0.05;

    public static Point getMidPoint(Point sp, Point ep) {
        return new Point((sp.x + ep.x) / 2, (sp.y + ep.y) / 2);
    }

    public static Point getCenter(Point[] points) {
        int x = 0, y = 0;
        for (Point p : points) {
            x += p.x;
            y += p.y;
        }
        x /= points.length;
        y /= points.length;
        return new Point(x, y);
    }

    public static boolean isNearby(Point a, Point b) {
        //System.out.println(a.distance(b));
        return a.distance(b) <= 2;
    }

    public static boolean isNearby(Point sp, Point ep, Point p) {
        double cross = (ep.x - sp.x) * (p.x - sp.x) + (ep.y - sp.y) * (p.y - sp.y);
        double d2 = (ep.x - sp.x) * (ep.x - sp.x) + (ep.y - sp.y) * (ep.y - sp.y);
        double MINDIST = 15;
        if (cross <= 0) return (Math.sqrt((p.x - sp.x) * (p.x - sp.x) + (p.y - sp.y) * (p.y - sp.y)) <= MINDIST);
        if (cross >= d2) return (Math.sqrt((p.x - ep.x) * (p.x - ep.x) + (p.y - ep.y) * (p.y - ep.y)) <= MINDIST);
        double r = cross / d2;
        double px = sp.x + (ep.x - sp.x) * r;
        double py = sp.y + (ep.y - sp.y) * r;
        return (Math.sqrt((p.x - px) * (p.x - px) + (py - p.y) * (py - p.y)) <= MINDIST);
    }

    public static void complexMove(int delta_x, int delta_y, Stack<Integer> xPoint, Stack<Integer> yPoint) {
        for (int i = 0; i < xPoint.size(); i++) {
            int temp = xPoint.get(i);
            xPoint.set(i, temp + delta_x);
            temp = yPoint.get(i);
            yPoint.set(i, temp + delta_y);
        }
    }

    public static boolean simpleClicked(Point p, Point sp, Point ep) {
        int min_x = Math.min(sp.x, ep.x);
        int max_x = Math.max(sp.x, ep.x);
        int min_y = Math.min(sp.y, ep.y);
        int max_y = Math.max(sp.y, ep.y);
        return p.x <= max_x && p.x >= min_x && p.y >= min_y && p.y <= max_y;
    }

    public static void drawBorder(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(Color.PINK);
        g.setStroke(new BasicStroke(4.0f));
        g.drawRect(x, y, width, height);
    }

    public static void complexDrawBorder(Graphics2D g, Stack<Integer> xPoint, Stack<Integer> yPoint) {
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
        Common.drawBorder(g, min_x, min_y, max_x - min_x, max_y - min_y);
    }

    public static Point[] simpleZoom(int scale, Point sp, Point ep) {
        if (sp.distance(ep) == 0) {
            if (scale == 1) {
                sp.x--;
                sp.y--;
                ep.x++;
                ep.y++;
            }
            return new Point[]{sp, ep};
        }
        Point formerCenter = getMidPoint(sp, ep);
        Point new_sp = new Point((int) (sp.x + scale * ZOOMRATE * (sp.x - ep.x)), (int) (sp.y + scale * ZOOMRATE * (sp.y - ep.y)));
        Point new_ep = new Point((int) (ep.x + scale * ZOOMRATE * (ep.x - sp.x)), (int) (ep.y + scale * ZOOMRATE * (ep.y - sp.y)));
        Point latterCenter = getMidPoint(new_sp, new_ep);
        sp.setLocation(new_sp.getX() + formerCenter.getX() - latterCenter.getX(), new_sp.getY() + formerCenter.getY() - latterCenter.getY());
        ep.setLocation(new_ep.getX() + formerCenter.getX() - latterCenter.getX(), new_ep.getY() + formerCenter.getY() - latterCenter.getY());
        return new Point[]{sp, ep};
    }

    public static void complexZoom(int scale, Stack<Integer> xPoint, Stack<Integer> yPoint) {
        Integer[] xs = new Integer[xPoint.size()];
        Integer[] ys = new Integer[yPoint.size()];
        xPoint.toArray(xs);
        yPoint.toArray(ys);
        Point[] points = new Point[xPoint.size()];
        for (int i = 0; i < xPoint.size(); i++)
            points[i] = new Point(xs[i], ys[i]);
        Point center = getCenter(points);
        for (int i = 0; i < xPoint.size(); i++) {
            xs[i] = xs[i] + (int) (scale * ZOOMRATE * (xs[i] - center.x));
            ys[i] = ys[i] + (int) (scale * ZOOMRATE * (ys[i] - center.y));
        }
        for (int i = 0; i < xPoint.size(); i++)
            points[i] = new Point(xs[i], ys[i]);
        Point newCenter = getCenter(points);
        for (int i = 0; i < xPoint.size(); i++) {
            xPoint.set(i, (int) (xs[i] + center.getX() - newCenter.getX()));
            yPoint.set(i, (int) (ys[i] + center.getY() - newCenter.getY()));
        }
    }

    public static int cross(int x1, int y1, int x2, int y2) {
        return x1 * y2 - x2 * y1;
    }

    public static int cross(Point a, Point b, Point c) {
        return cross(b.x - a.x, b.y - a.y, c.x - a.x, c.y - a.y);
    }

    public static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}
