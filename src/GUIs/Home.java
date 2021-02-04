package src.GUIs;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home {

    GUIContainer container;
    JPanel mainPanel, imagePanel;
    JLabel image;
    BufferedImage img;

    public Home()
    {
        container = new GUIContainer();
        container.frame.setTitle("Home");
        mainPanel = container.contentPanel;
        image = new JLabel();
        imagePanel = new JPanel();
        try {
            img = ImageIO.read(new File("src\\Images\\fireplace-main.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(container.frame.getWidth(), container.frame.getHeight() / 2,Image.SCALE_SMOOTH);
        ImageIcon fireplace = new ImageIcon(dimg);
        image.setIcon(fireplace);
        imagePanel.add(image);
        GUISuper.addComponent(mainPanel, imagePanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        container.frame.setVisible(true);
    }
}