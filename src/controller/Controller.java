package controller;

import controller.state.*;
import model.Model;
import model.common.Tool;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private ArrayList<ActionListener> toolListeners = new ArrayList<>();
    private ArrayList<MouseListener> colorListeners = new ArrayList<>();
    private State curState;
    private static Controller curCtrl;

    public Controller() {
        curCtrl = this;
        State.states[Tool.IDLE] = new Start();
        State.states[Tool.LINE] = new TwoPointGenerator();
        State.states[Tool.POINTER] = new MoveOperator();
        State.states[Tool.RECT] = State.states[Tool.LINE];
        State.states[Tool.ELLIPSE] = State.states[Tool.LINE];
        State.states[Tool.POLY] = new MultiplePointGenerator();
        State.states[Tool.FOLD] = State.states[Tool.POLY];
        curState = State.getState(Tool.IDLE);
    }

    static public Controller getCurCtrl() {
        return curCtrl;
    }

    public void bindModel(Model model) {
        this.model = model;
    }

    public ActionListener createToolListener(int typeCode) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeCode == Tool.TRASH) {
                    model.clear();
                    model.setToolType(Tool.POINTER);
                }
                else model.setToolType(typeCode);
                curState = State.getState(Tool.IDLE);
            }
        };
    }

    public MouseListener createColorListener(Color color) {
        /*
        ActionListener colorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setColor(color);
            }
        };        */
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    model.setColor(color,0);
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    model.setColor(color,1);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public MouseListener createMouseListener() {
        return new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    curState = curState.leftRelease(e);
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    curState = curState.rightRelease(e);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 触发以后记录前后两个点的位置，然后绘制相应的图像，让model触发view的更新函数好了
                if (e.getButton() == MouseEvent.BUTTON1)   //判断如果按下鼠标左键
                    curState = curState.leftClick(e);
                else if (e.getButton() == MouseEvent.BUTTON3) //判断如果按下鼠标右键
                    curState = curState.rightClick(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    public MouseMotionListener createMouseMotionListener() {
        return new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
                curState = curState.move(e);
            }

            public void mouseDragged(MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK)   //判断如果鼠标左键拖拽
                {
                    curState = curState.leftDrag(e);
                }
                else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {//判断如果鼠标右键拖拽
//                    curState = curState.rightDrag(e);
                }
            }
        };
    }

    public ChangeListener createChangeListener() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Model.getCurModel().switchFilled();
            }
        };
    }

    public ActionListener createSaveListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getCurModel().saveToFile();
            }
        };
    }

    public ActionListener createReadListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getCurModel().readFromFile();
            }
        };
    }
}
