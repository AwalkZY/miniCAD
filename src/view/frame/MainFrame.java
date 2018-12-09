package view.frame;

import controller.Controller;
import view.panel.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private CanvasPanel canvasPanel = new CanvasPanel();
    private ToolPanel toolPanel = new ToolPanel();

    public MainFrame() {
        this(800,800); //默认长宽各700
    }

    public MainFrame(int height, int width) {
        setTitle("MiniCAD"); //设置界面标题
        setSize(width,height); //设置界面宽度与高度
        BorderLayout borderLayout = new BorderLayout();  //新建BorderLayout布局管理器
        setLayout(borderLayout);  //设置布局管理器

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置界面默认关闭方式
        setVisible(true); //设置界面是否可视
        setAlwaysOnTop(true); //设置界面是否固定在最顶端
        setBackground(Color.WHITE); //设置背景颜色为白色
        add(BorderLayout.CENTER,canvasPanel);  //向当前界面添加绘图面板
        add(BorderLayout.EAST,toolPanel);  //向当前界面添加操作面板
    }

    public void bindController(Controller ctrl) {
        toolPanel.bindController(ctrl);
    }
}
