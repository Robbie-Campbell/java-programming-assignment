package src.GUIs;

import javax.swing.*;

public class Home {

    GUIContainer container;
    JPanel mainPanel, imagePanel;
    JLabel image;

    public Home()
    {
        container = new GUIContainer();
        container.frame.setTitle("Home");
        mainPanel = container.contentPanel;
        container.frame.setVisible(true);
    }


    public static void main(String[] args){
        new Home();
    }
}