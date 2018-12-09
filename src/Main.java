import controller.Controller;
import model.Model;
import view.View;

public class Main{
    public static void main(String[] args) {
        try {
            Controller ctrl = new Controller();
            Model model = new Model();
            View view = new View();
            ctrl.bindModel(model);
            model.bindController(ctrl);
            model.bindView(view);
            view.bindModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
