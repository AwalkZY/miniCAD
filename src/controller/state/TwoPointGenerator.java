package controller.state;

import model.Model;
import model.common.Tool;

import java.awt.event.MouseEvent;

public class TwoPointGenerator implements State{
    @Override
    public State leftClick(MouseEvent e) {
        Model.getCurModel().expandShape(e.getPoint());
        return State.getState(Tool.IDLE);
    }

    @Override
    public State rightClick(MouseEvent e) {
        Model.getCurModel().rollbackShape();
        return State.getState(Tool.IDLE);
    }

    @Override
    public State move(MouseEvent e) {
        Model.getCurModel().modifyShape(e.getPoint());
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
