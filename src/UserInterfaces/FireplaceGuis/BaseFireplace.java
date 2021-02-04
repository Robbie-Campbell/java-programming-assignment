package src.UserInterfaces.FireplaceGuis;

import javax.swing.*;

import src.UserInterfaces.*;

import java.awt.*;
import java.awt.event.*;

public class BaseFireplace implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, decisionPanel;
    private JButton insertFireplace, indexFireplace;

    // CONSTRUCTOR METHOD
    public BaseFireplace() {

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("Fireplace Directory");
        mainPanel = container.contentPanel;

        // Create the decision panel
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, decisionPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Insert a new supplier button
        insertFireplace = new JButton("Insert a New Fireplace");
        insertFireplace.setFont(container.contentFont);
        GUISuper.addComponent(decisionPanel, insertFireplace, 0, 0, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        insertFireplace.addActionListener(this);

        // List all of suppliers in database button
        indexFireplace = new JButton("Show All Fireplace Information");
        indexFireplace.setFont(container.contentFont);
        GUISuper.addComponent(decisionPanel, indexFireplace, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        indexFireplace.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        // Insert a new supplier
        if (e.getSource() == insertFireplace) 
        {
            new InsertFireplace();
            container.frame.dispose();
        }

        // List all of the suppliers
        if (e.getSource() == indexFireplace) 
        {
            new FireplaceIndex();
            container.frame.dispose();
        }
    }
}