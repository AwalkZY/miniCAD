package controller.state;

import model.Model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class MoveOperator implements State {
    private Point lastPoint;

    @Override
    public State leftClick(MouseEvent e) {
        try {
            Model.getCurModel().initOperation(e.getPoint());
            lastPoint = null;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e1) {
            e1.printStackTrace();
        }
        return this;
    }

    @Override
    public State rightClick(MouseEvent e) {
        Model.getCurModel().removeShape();
        return this;
    }

    @Override
    public State move(MouseEvent e) {
        return this;
    }

    @Override
    public State leftDrag(MouseEvent e) {
        if (lastPoint != null) {
            Model.getCurModel().moveShape(e.getX() - lastPoint.x, e.getY() - lastPoint.y);
        } else {
            if (!Model.getCurModel().clickCurShape(e.getPoint())) return this;
        }
        lastPoint = e.getPoint();
        return this;
    }

    @Override
    public State rightDrag(MouseEvent e) {
        return this;
    }

    @Override
    public State leftRelease(MouseEvent e) {
        lastPoint = null;
        return this;
    }

    @Override
    public State rightRelease(MouseEvent e) {
        return this;
    }
}
