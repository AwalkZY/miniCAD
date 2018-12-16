package view.panel;

import controller.Controller;
import model.Model;
import model.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CanvasPanel extends JPanel {
    private static final long serialVersionUID = 1L; //设置版本UID信息

    private int height = 2000, width = 2000;

    private BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  //新建画布

    private int curType = -1;

    public CanvasPanel() {
        setBackground(Color.WHITE);  //初始化背景颜色为白色
        addMouseListener(Controller.getCurCtrl().createMouseListener());
        addMouseMotionListener(Controller.getCurCtrl().createMouseMotionListener());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;  //向下造型，转化为Graphics2D再绘制
        drawAll(g2); //将BufferedImage的图像绘制到当前panel上
    }

    private void drawAll(Graphics2D g) {
        g.drawImage(bImage, 0, 0, width, height, null); //将画布绘制在Panel上
    }

    public void reRender() {
        bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bImage.getGraphics().setColor(Model.getCurModel().getColor());
        ArrayList<Shape> shapeList = Model.getCurModel().getShapes();
        for (Shape shape : shapeList) shape.draw((Graphics2D) bImage.getGraphics());
        Shape shape = Model.getCurModel().getChosenShape();
        if (shape != null) shape.drawBorder((Graphics2D) bImage.getGraphics());
        repaint();
    }
}