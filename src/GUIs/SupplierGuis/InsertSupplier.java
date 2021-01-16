package src.GUIs.SupplierGuis;

import java.util.*;
import javax.swing.*;

import com.mysql.cj.protocol.Warning;

import src.AllClasses.Supplier;
import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;

public class InsertSupplier implements ActionListener {
    GUIContainer container;
    JPanel mainPanel, decisionPanel;
    JLabel idPrompt, namePrompt, locationPrompt, contactPrompt, emailPrompt;
    JTextField nameInput, locationInput, contactInput, emailInput;
    JComboBox listOfIds;
    JButton insertSupplier;

    public InsertSupplier() {
        container = new GUIContainer();
        container.frame.setTitle("Create New Supplier");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, decisionPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create name update area
        namePrompt = new JLabel("Insert Name of Supplier");
        nameInput = new JTextField();
        nameInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, namePrompt, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, nameInput, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create location update area
        locationPrompt = new JLabel("Insert Location of Supplier");
        locationInput = new JTextField();
        locationInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, locationPrompt, 0, 4, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, locationInput, 0, 5, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create contact update area
        contactPrompt = new JLabel("Insert Contact Number of Supplier");
        contactInput = new JTextField();
        contactInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, contactPrompt, 0, 6, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, contactInput, 0, 7, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create email update area
        emailPrompt = new JLabel("Insert Email Address of Supplier");
        emailInput = new JTextField();
        emailInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, emailPrompt, 0, 8, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, emailInput, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Submit an update to the db
        insertSupplier = new JButton("Insert this supplier information");
        GUISuper.addComponent(decisionPanel, insertSupplier, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        insertSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insertSupplier) 
        {
            Supplier insert = new Supplier(nameInput.getText(), locationInput.getText(), contactInput.getText(), emailInput.getText());
            if (!nameInput.getText().equals("") && !locationInput.getText().equals("") && !contactInput.getText().equals("") && !emailInput.getText().equals(""))
            {
                int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to Create Supplier: " + nameInput.getText() + "?", "Create User",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION)
                {
                insert.insertSupplierIntoDB();
                JOptionPane.showMessageDialog(container.frame, "Successfully Created Supplier!");
                container.frame.dispose();
                new Home();
                }
                else
                {
                    JOptionPane.showMessageDialog(container.frame, "Operation Cancelled");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(container.frame, "Please input data into all text boxes", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
