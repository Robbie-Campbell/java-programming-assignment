package src.GUIs.SupplierGuis;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;

public class BaseSupplier implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, decisionPanel;
    private JButton insertSupplier, listSupplier;

    // CONSTRUCTOR METHOD
    public BaseSupplier() {

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("Supplier Directory");
        mainPanel = container.contentPanel;

        // Create the decision panel
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, decisionPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Insert a new supplier button
        insertSupplier = new JButton("Insert a New Supplier");
        insertSupplier.setFont(container.contentFont);
        GUISuper.addComponent(decisionPanel, insertSupplier, 0, 0, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        insertSupplier.addActionListener(this);

        // List all of suppliers in database button
        listSupplier = new JButton("Show All Supplier Information");
        listSupplier.setFont(container.contentFont);
        GUISuper.addComponent(decisionPanel, listSupplier, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        listSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        // Insert a new supplier
        if (e.getSource() == insertSupplier) 
        {
            new InsertSupplier();
            container.frame.dispose();
        }

        // List all of the suppliers
        if (e.getSource() == listSupplier) 
        {
            new SupplierIndex();
            container.frame.dispose();
        }
    }
}