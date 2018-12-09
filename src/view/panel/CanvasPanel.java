package view.panel;

import model.common.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.EventObject;

public class CanvasPanel extends JPanel{
    private static final long serialVersionUID = 1L; //设置版本UID信息

    private int height = 800, width = 800;

    private BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  //新建画布

    private int curType = -1;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;  //向下造型，转化为Graphics2D再绘制
        drawAll(g2); //将BufferedImage的图像绘制到当前panel上
    }

    public void clear() {
        bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);   //清空画布
        repaint(); //重新绘制
    }

    public void bindMouseEvents() {
        addMouseListener(new MouseListener(){
            @Override
            public void mousePressed(MouseEvent e)
            {

            }
            @Override
            public void mouseReleased(MouseEvent e)
            {

            }
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e){}

            public void mouseDragged(MouseEvent e)
            {

            }
        });
    }

    public CanvasPanel() {
        setBackground(Color.WHITE);  //初始化背景颜色为白色
        bindMouseEvents(); //绑定鼠标事件
    }

    private void drawAll(Graphics2D g) {
        g.drawImage(bImage,0,0,width, height,null); //将画布绘制在Panel上
    }
}