package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import src.GUIs.*;

public class GUIContainer implements ActionListener{

    // Initialise variables
    JFrame frame;
    JPanel navbar, contentPanel;
    JLabel title;
    JButton home, fireplaces, suppliers;
    Font mainFont;
    Color flame, white;

    public GUIContainer() {

        // Create style elements
        mainFont = new Font("SansSerif", Font.BOLD, 30);
        flame = new Color(226, 88, 34);
        white = new Color(240, 240, 240);

        // Create a frame
        frame = new JFrame();
        frame.setResizable(false);

        // Create the main panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        frame.add(contentPanel);

        // Create the navbar
        navbar = new JPanel();
        navbar.setLayout(new GridBagLayout());
        navbar.setBackground(flame);
        GUISuper.addComponent(contentPanel, navbar, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create the layout type for the navbar
        navbar.setLayout(new GridBagLayout());

        // Create the title of the navbar
        title = new JLabel("Chesneys Fireplaces");
        title.setFont(mainFont);
        title.setForeground(white);
        GUISuper.addComponent(navbar, title, 0, 0, 4, 2, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);

        // Add the home button to the navbar
        home = new JButton("Home");
        home.setBackground(white);
        home.addActionListener(this);
        GUISuper.addComponent(navbar, home, 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Add the supplier button to the navbar
        suppliers = new JButton("Suppliers");
        suppliers.setBackground(white);
        suppliers.addActionListener(this);
        GUISuper.addComponent(navbar, suppliers, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Add the fireplace button to the navbar
        fireplaces = new JButton("Fireplaces");
        fireplaces.setBackground(white);
        fireplaces.addActionListener(this);
        GUISuper.addComponent(navbar, fireplaces, 3, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        // Home Page
        if (e.getSource() == home)
        {
            frame.dispose();
            new Home();
        }

        // Supplier page
        if (e.getSource() == suppliers)
        {
            frame.dispose();
            new SupplierGUI();
        }

        // Fireplaces page
        if (e.getSource() == fireplaces)
        {
            frame.dispose();
            new FireplaceGUI();
        }
    }

}
