package controller.state;

import model.Model;
import model.common.Common;
import model.common.Tool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MultiplePointGenerator implements State {
    private Point lastPoint;

    @Override
    public State leftClick(MouseEvent e) {
        Model.getCurModel().expandShape(e.getPoint(), false);
        if (lastPoint != null && Common.isNearby(lastPoint, e.getPoint())) {
            return State.getState(Tool.IDLE);
        }
        lastPoint = e.getPoint();
        return this;
    }

    @Override
    public State rightClick(MouseEvent e) {
        if (Model.getCurModel().rollbackShape()) {
            Model.getCurModel().modifyShape(e.getPoint(), false);
            return this;
        }
        return State.getState(Tool.IDLE);
    }

    @Override
    public State move(MouseEvent e) {
        Model.getCurModel().modifyShape(e.getPoint(), false);
        return this;
    }

    @Override
    public State leftDrag(MouseEvent e) {
        return this;
    }

    @Override
    public State rightDrag(MouseEvent e) {
        return this;
    }

    @Override
    public State leftRelease(MouseEvent e) {
        return this;
    }

    @Override
    public State rightRelease(MouseEvent e) {
        return this;
    }
}
