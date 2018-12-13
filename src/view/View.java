package view;
import controller.Controller;
import model.Model;
import view.frame.MainFrame;

import java.awt.*;

public class View {
    private Model model;
    private MainFrame mainFrame;
    static private View curView;

    public static View getCurView(){
        return curView;
    }

    public View(){
        this.mainFrame = new MainFrame();
        curView = this;
    }

    public void bindModel(Model model){
        this.model = model;
    }

    public void reRender() {
        this.mainFrame.reRender();
    }
}
