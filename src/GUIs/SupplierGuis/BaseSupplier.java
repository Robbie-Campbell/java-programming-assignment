package src.GUIs.SupplierGuis;

import java.util.*;
import javax.swing.*;

import com.mysql.cj.protocol.Warning;

import src.AllClasses.Supplier;
import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;

public class BaseSupplier implements ActionListener {
    GUIContainer container;
    JPanel mainPanel, decisionPanel;
    JButton insertSupplier, updateSupplier, listSupplier, deleteSupplier;

    public BaseSupplier() {
        container = new GUIContainer();
        container.frame.setTitle("Supplier Directory");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, decisionPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Insert a new supplier button
        insertSupplier = new JButton("Insert a New Supplier");
        GUISuper.addComponent(decisionPanel, insertSupplier, 0, 0, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        insertSupplier.addActionListener(this);

         // Update a supplier button
         updateSupplier = new JButton("Update Supplier Information");
         GUISuper.addComponent(decisionPanel, updateSupplier, 0, 1, 1, 1, GridBagConstraints.CENTER,
                 GridBagConstraints.BOTH);
         updateSupplier.addActionListener(this);

        // List all of suppliers in database button
        listSupplier = new JButton("Show All Supplier Information");
        GUISuper.addComponent(decisionPanel, listSupplier, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        listSupplier.addActionListener(this);

        // Delete a supplier from the database
        deleteSupplier = new JButton("Delete a Supplier");
        GUISuper.addComponent(decisionPanel, deleteSupplier, 0, 3, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        deleteSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insertSupplier) 
        {
            new InsertSupplier();
            container.frame.dispose();
        }
        if (e.getSource() == updateSupplier) 
        {
            new UpdateSupplier();
            container.frame.dispose();
        }
        if (e.getSource() == listSupplier) 
        {
            new SupplierIndex();
            container.frame.dispose();
        }

        if (e.getSource() == deleteSupplier) 
        {
            new DeleteSupplier();
            container.frame.dispose();
        }
    }
}