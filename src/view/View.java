package view;
import controller.Controller;
import model.Model;
import view.frame.MainFrame;

public class View {
    private Model model;
    private MainFrame mainFrame;

    public View(){
        this.mainFrame = new MainFrame();
    }

    public void bindModel(Model model){
        this.model = model;
    }

    void bindController(Controller ctrl){
        mainFrame.bindController(ctrl);
    }
}
