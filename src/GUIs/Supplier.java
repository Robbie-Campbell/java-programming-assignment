package src.GUIs;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Supplier {
    GUIContainer container;
    JPanel mainPanel, imagePanel;
    JLabel image;

    public Supplier()
    {
        container = new GUIContainer();
        container.frame.setBounds(100,100,700,600);
        container.frame.setTitle("Suppliers");
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
}
