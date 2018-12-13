package model.shape;

import java.awt.*;

public class Text extends Shape {
    private String content;

    Text(){
        content = "";
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void expand(Point p) {

    }

    @Override
    public void modify(Point p) {

    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public boolean isClicked(Point p) {
        return false;
    }

    @Override
    public void move(int delta_x, int delta_y) {

    }

    @Override
    public void drawBorder(Graphics2D g) {

    }
}
