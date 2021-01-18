package src.GUIs.FireplaceGuis;

import javax.swing.*;
import java.awt.*;
import src.GUIs.*;

public class BaseFireplace {

    GUIContainer container;
    JPanel mainPanel, imagePanel;
    JLabel image;

    public BaseFireplace()
    {
        container = new GUIContainer();
        container.frame.setTitle("Fireplace");
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
