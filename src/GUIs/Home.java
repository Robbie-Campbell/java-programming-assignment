package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Home {

    GUIContainer container;
    JPanel mainPanel, imagePanel;
    JLabel image;

    public Home()
    {
        container = new GUIContainer();
        container.frame.setBounds(100,100,700,600);
        container.frame.setTitle("Home");
        mainPanel = container.contentPanel;
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(0,0,0));
        GUISuper.addComponent(mainPanel, imagePanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        ImageIcon fireplace = new ImageIcon("src\\Images\\home.jpg");
        image = new JLabel();
        image.setIcon(fireplace);
        imagePanel.add(image);
        container.frame.setVisible(true);
    }


    public static void main(String[] args){
        new Home();
    }
}