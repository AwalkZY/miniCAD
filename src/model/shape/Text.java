package model.shape;

import model.common.Common;

import java.awt.*;

public class Text extends Shape {
    private String content;
    private Point sp;
    private int x;
    private int y;
    private int width;
    private int height;
    private int fontSize = 12;

    public Text(Point p) {
        content = "";
        sp = p;
    }

    public Text clone() throws CloneNotSupportedException {
        Text newText = (Text) super.clone();
        newText.sp = (Point) sp.clone();
        newText.content = content.intern();
        return newText;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(this.contourColor);
        Font font = g.getFont();
        String fontName = font.getName();
        g.setFont(new Font(fontName, Font.PLAIN, fontSize));
        g.drawString(content, sp.x, sp.y);
        FontMetrics me = g.getFontMetrics();
        x = sp.x - 4;
        y = sp.y - height + 8;
        width = (int) me.getStringBounds(content, g).getWidth() + 8;
        height = (int) me.getStringBounds(content, g).getHeight() + 8;
    }

    @Override
    public void expand(Point p, boolean equal) {

    }

    @Override
    public void modify(Point p, boolean equal) {

    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public void modifyText(String Text) {
        if (Text != null && !Text.equals("")) this.content = Text;
    }

    @Override
    public boolean isClicked(Point p) {
        return Common.simpleClicked(p, new Point(x, y), new Point(x + width, y + height));
    }

    @Override
    public void move(int delta_x, int delta_y) {
        sp.x += delta_x;
        sp.y += delta_y;
    }

    @Override
    public void drawBorder(Graphics2D g) {
        Common.drawBorder(g, x, y, width, height);
    }

    @Override
    public void zoomShape(int scale) {
        fontSize += scale;
    }
}
