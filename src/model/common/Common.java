package model.common;

import model.shape.Line;

import java.awt.*;
import java.util.Stack;

public class Common {
    private static double EPS = 2;
    private static double MINDIST = 15;

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
        System.out.println(a.distance(b));
        return a.distance(b) <= EPS;
    }

    //TODO: 判断各个形状是否被点击
    //TODO: 画圆、正方形

    public static boolean isNearby(Point sp, Point ep, Point p) {
        double cross = (ep.x - sp.x) * (p.x - sp.x) + (ep.y - sp.y) * (p.y - sp.y);
        double d2 = (ep.x - sp.x) * (ep.x - sp.x) + (ep.y - sp.y) * (ep.y - sp.y);
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
}
