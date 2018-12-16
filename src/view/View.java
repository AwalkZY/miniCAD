package view;

import view.frame.MainFrame;

import javax.swing.*;

public class View {
    static private View curView;
    private MainFrame mainFrame;

    public View() {
        this.mainFrame = new MainFrame();
        curView = this;
    }

    public static View getCurView() {
        return curView;
    }

    public void reRender() {
        this.mainFrame.reRender();
    }


    public JFrame getMainFrame() {
        return this.mainFrame;
    }
}
