package src.UserInterfaces.SupplierGuis;

/*
Author: Robbie Campbell
Date: 04/02/2021
Description:
This class allows the user to insert a new supplier into the database
*/

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import src.Logic.Supplier;
import src.UserInterfaces.*;

public class InsertSupplier implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, insertSupplierPanel;
    private JLabel businessNamePrompt, collectionNamePrompt, namePrompt, locationPrompt, contactPrompt, emailPrompt;
    private JTextField businessNameInput, collectionNameInput, nameInput, locationInput, contactInput, emailInput;
    private JButton insertSupplier;

    // CONSTRUCTOR METHOD
    public InsertSupplier() {

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("Create New Supplier");
        mainPanel = container.contentPanel;

        // Create the insert supplier panel
        insertSupplierPanel = new JPanel();
        insertSupplierPanel.setLayout(new GridBagLayout());
        insertSupplierPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, insertSupplierPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 20);

        // Create business name input area
        businessNamePrompt = new JLabel("Insert Business Name of Supplier");
        businessNamePrompt.setFont(container.contentFont);
        businessNameInput = new JTextField();
        GUISuper.addComponent(insertSupplierPanel, businessNamePrompt, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertSupplierPanel, businessNameInput, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create collection name input area
        collectionNamePrompt = new JLabel("Insert Collection Name of Supplier");
        collectionNamePrompt.setFont(container.contentFont);
        collectionNameInput = new JTextField();
        GUISuper.addComponent(insertSupplierPanel, collectionNamePrompt, 0, 4, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertSupplierPanel, collectionNameInput, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create supplier name input area
        namePrompt = new JLabel("Insert Name of Supplier");
        namePrompt.setFont(container.contentFont);
        nameInput = new JTextField();
        GUISuper.addComponent(insertSupplierPanel, namePrompt, 0, 6, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertSupplierPanel, nameInput, 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create location input area
        locationPrompt = new JLabel("Insert Location of Supplier");
        locationPrompt.setFont(container.contentFont);
        locationInput = new JTextField();
        GUISuper.addComponent(insertSupplierPanel, locationPrompt, 0, 8, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertSupplierPanel, locationInput, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create contact number input area
        contactPrompt = new JLabel("Insert Contact Number of Supplier");
        contactPrompt.setFont(container.contentFont);
        contactInput = new JTextField();
        GUISuper.addComponent(insertSupplierPanel, contactPrompt, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertSupplierPanel, contactInput, 0, 11, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create email input area
        emailPrompt = new JLabel("Insert Email Address of Supplier");
        emailPrompt.setFont(container.contentFont);
        emailInput = new JTextField();
        GUISuper.addComponent(insertSupplierPanel, emailPrompt, 0, 12, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertSupplierPanel, emailInput, 0, 13, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Submit an supplier to the db
        insertSupplier = new JButton("Create Supplier");
        insertSupplier.setBackground(container.confirmationBlue);
        insertSupplier.setBorder(container.raisedBorder);
        insertSupplier.setFont(container.contentFont);
        GUISuper.addComponent(insertSupplierPanel, insertSupplier, 0, 14, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        insertSupplier.addActionListener(this);

        // Set the frame as vidsible
        container.frame.setVisible(true);
    }

    // Method to add a new supplier to the database
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insertSupplier) 
        {

            // Create an instance of the supplier class with the textbox information
            Supplier insert = new Supplier(businessNameInput.getText(), collectionNameInput.getText(), nameInput.getText(), locationInput.getText(), contactInput.getText(), emailInput.getText(), "src\\Images\\no-image.png");

            // Check to make sure that all textboxes are not empty
            if (!nameInput.getText().equals("") && !locationInput.getText().equals("") && !contactInput.getText().equals("") && !emailInput.getText().equals(""))
            {

                // Make the user confirm their decision to add a new supplier
                int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to create the Supplier: " + nameInput.getText() + "?", "Create User",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

                // Inform the user of the success of the insert
                if(result == JOptionPane.YES_OPTION)
                {
                        insert.insertIntoDB();
                        JOptionPane.showMessageDialog(container.frame, "Successfully Created Supplier!");
                        container.frame.dispose();
                        new Home();
                }

                // Cancel the operation
                else
                {
                    JOptionPane.showMessageDialog(container.frame, "Operation Cancelled");
                }
            }

            // Prompt the user to input information in all areas
            else
            {
                JOptionPane.showMessageDialog(container.frame, "Please input data into all text boxes", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
