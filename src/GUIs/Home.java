package src.GUIs;

import javax.swing.*;
import java.awt.*;

public class Home {

    GUIContainer container;
    JPanel mainPanel, imagePanel;
    JLabel image;

    public Home()
    {
        container = new GUIContainer();
        container.frame.setTitle("Home");
        mainPanel = container.contentPanel;
        imagePanel = new JPanel();
        ImageIcon fireplace = new ImageIcon("src\\Images\\fireplace-main.jpg");
        image = new JLabel();
        image.setIcon(fireplace);
        imagePanel.add(image);
        GUISuper.addComponent(mainPanel, imagePanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        container.frame.setVisible(true);
    }
}