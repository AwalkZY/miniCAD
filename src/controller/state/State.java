package controller.state;

import model.common.Tool;

import java.awt.event.MouseEvent;

public interface State {
    State leftClick(MouseEvent e);
    State rightClick(MouseEvent e);
    State move(MouseEvent e);
    State leftDrag(MouseEvent e);
    State rightDrag(MouseEvent e);
    State leftRelease(MouseEvent e);
    State rightRelease(MouseEvent e);
    State[] states = new State[Tool.NUM];
    static public State getState(int toolType){
        System.out.println("Getting into: "+toolType);
        return states[toolType];
    }
}
