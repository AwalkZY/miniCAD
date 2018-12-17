package controller.state;

import model.Model;
import model.common.Tool;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class Start implements State {
    @Override
    public State leftClick(MouseEvent e) {
        try {
            Model.getCurModel().initOperation(e.getPoint());
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return State.getState(Model.getCurModel().getToolType());
    }

    @Override
    public State rightClick(MouseEvent e) {
        Model.getCurModel().setToolType(Tool.POINTER);
        return this;
    }

    @Override
    public State move(MouseEvent e) {
        return this;
    }

    @Override
    public State leftDrag(MouseEvent e) {
        return this;
    }

    @Override
    public State leftRelease(MouseEvent e) {
        return this;
    }
}
