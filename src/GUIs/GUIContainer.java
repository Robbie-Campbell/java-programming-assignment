package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.GUIs.SupplierGuis.*;
import src.GUIs.FireplaceGuis.BaseFireplace;
import javax.swing.border.Border;

public class GUIContainer implements ActionListener {

    // INITIALISE VARIABLES
    public JFrame frame;
    public JPanel navbar, contentPanel;
    public Font titleFont, contentFont;
    public Border raisedBorder;
    public Color flame, white, updateGreen, dangerRed, confirmationBlue;
    private JLabel title;
    private JButton home, fireplaces, suppliers;

    public GUIContainer() {

        // Create style elements
        raisedBorder = BorderFactory.createRaisedBevelBorder();
        titleFont = new Font("SansSerif", Font.BOLD, 30);
        contentFont = new Font("SansSerif", Font.BOLD, 14);
        flame = new Color(226, 88, 34);
        white = new Color(240, 240, 240);
        updateGreen = new Color(144, 238, 144);
        dangerRed = new Color(130, 0, 0);
        confirmationBlue = new Color(176, 226, 255);

        // Create a frame
        frame = new JFrame();
        frame.setBounds(100,100,600,700);

        // Create the main panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        frame.add(contentPanel);

        // Create the navbar
        navbar = new JPanel();
        navbar.setLayout(new GridBagLayout());
        navbar.setBackground(flame);
        navbar.setLayout(new GridBagLayout());
        GUISuper.addComponent(contentPanel, navbar, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create the title of the navbar
        title = new JLabel("Chesneys Fireplaces");
        title.setFont(titleFont);
        title.setForeground(white);
        GUISuper.addComponent(navbar, title, 0, 0, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);

        // Add the home button to the navbar
        home = new JButton("Home");
        home.setFont(contentFont);
        home.setBackground(white);
        home.addActionListener(this);
        GUISuper.addComponent(navbar, home, 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Add the supplier button to the navbar
        suppliers = new JButton("Suppliers");
        suppliers.setFont(contentFont);
        suppliers.setBackground(white);
        suppliers.addActionListener(this);
        GUISuper.addComponent(navbar, suppliers, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Add the fireplace button to the navbar
        fireplaces = new JButton("Fireplaces");
        fireplaces.setFont(contentFont);
        fireplaces.setBackground(white);
        fireplaces.addActionListener(this);
        GUISuper.addComponent(navbar, fireplaces, 3, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        // Home Page
        if (e.getSource() == home) {
            frame.dispose();
            new Home();
        }

        // Supplier page
        if (e.getSource() == suppliers) {
            frame.dispose();
            new BaseSupplier();
        }

        // Fireplaces page
        if (e.getSource() == fireplaces) {
            frame.dispose();
            new BaseFireplace();
        }
    }

}
