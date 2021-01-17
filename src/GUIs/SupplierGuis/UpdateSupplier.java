package src.GUIs.SupplierGuis;

import javax.swing.*;

import src.AllClasses.Supplier;
import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;

public class UpdateSupplier implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, updateInfoPanel;
    private JLabel showSupplierForUpdate, namePrompt, locationPrompt, contactPrompt, emailPrompt;
    private JTextField nameInput, locationInput, contactInput, emailInput;
    private JButton updateSupplier, deleteSupplier;
    private int supplierID;
    private Font mainFont;

    // CONSTRUCTOR METHOD
    public UpdateSupplier(Supplier supplier) {

        // Create style variables
        mainFont = new Font("SansSerif", Font.BOLD, 18);

        // Extend the navbar
        supplierID = supplier.getID();
        container = new GUIContainer();
        container.frame.setTitle("Update a Supplier");
        mainPanel = container.contentPanel;

        // Create the update information panel
        updateInfoPanel = new JPanel();
        updateInfoPanel.setLayout(new GridBagLayout());
        updateInfoPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, updateInfoPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Get all of the information of the supplier
        String[] selectedSupplier = StaticDatabaseMethods.getSupplierFromDB(supplier.getID()).getAllInfo();

        // Tell the user which supplier they are updating
        showSupplierForUpdate = new JLabel("UPDATING INFORMATION FOR SUPPLIER: " + selectedSupplier[0]);
        showSupplierForUpdate.setFont(mainFont);
        GUISuper.addComponent(updateInfoPanel, showSupplierForUpdate, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 0, 20);

        // Create name update area
        namePrompt = new JLabel("Update the name of this person");
        namePrompt.setFont(container.contentFont);
        nameInput = new JTextField(selectedSupplier[1]);
        nameInput.setColumns(10);
        GUISuper.addComponent(updateInfoPanel, namePrompt, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 0, 5);
        GUISuper.addComponent(updateInfoPanel, nameInput, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create location update area
        locationPrompt = new JLabel("Update the location of this person");
        locationInput = new JTextField(selectedSupplier[2]);
        locationPrompt.setFont(container.contentFont);
        locationInput.setColumns(10);
        GUISuper.addComponent(updateInfoPanel, locationPrompt, 0, 4, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 0, 5);
        GUISuper.addComponent(updateInfoPanel, locationInput, 0, 5, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create contact update area
        contactPrompt = new JLabel("Update the contact number of this person");
        contactInput = new JTextField(selectedSupplier[3]);
        contactPrompt.setFont(container.contentFont);
        contactInput.setColumns(10);
        GUISuper.addComponent(updateInfoPanel, contactPrompt, 0, 6, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 0, 5);
        GUISuper.addComponent(updateInfoPanel, contactInput, 0, 7, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create email update area
        emailPrompt = new JLabel("Update the email of this person");
        emailInput = new JTextField(selectedSupplier[4]);
        emailPrompt.setFont(container.contentFont);
        emailInput.setColumns(10);
        GUISuper.addComponent(updateInfoPanel, emailPrompt, 0, 8, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 0, 5);
        GUISuper.addComponent(updateInfoPanel, emailInput, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Submit an update to the db
        updateSupplier = new JButton("Update this supplier information");
        updateSupplier.setFont(container.contentFont);
        updateSupplier.setBackground(container.updateGreen);
        updateSupplier.setBorder(container.raisedBorder);
        GUISuper.addComponent(updateInfoPanel, updateSupplier, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        updateSupplier.addActionListener(this);

        // Delete the currently selected supplier
        deleteSupplier = new JButton("Delete this supplier");
        deleteSupplier.setFont(container.contentFont);
        deleteSupplier.setBackground(container.dangerRed);
        deleteSupplier.setForeground(container.white);
        deleteSupplier.setBorder(container.raisedBorder);
        GUISuper.addComponent(updateInfoPanel, deleteSupplier, 0, 11, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        deleteSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Method to update the supplier in the database
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateSupplier) 
        {

            // Create an instance of the supplier class and set all supplier information from textboxes 
            Supplier update = StaticDatabaseMethods.getSupplierFromDB(supplierID);
            update.setName(nameInput.getText());
            update.setLocation(locationInput.getText());
            update.setContact(contactInput.getText());
            update.setEmail(emailInput.getText());

            // Make sure that the information being entered is not blank
            if (!nameInput.getText().equals("") && !locationInput.getText().equals("") && !contactInput.getText().equals("") && !emailInput.getText().equals(""))
            {

                // Make the user confirm their decision to update the user
                int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to update user: " + supplierID + "?", "Confirm Update",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

                // Successfully complete the operation and inform the user
                if(result == JOptionPane.YES_OPTION)
                {
                    update.updateRowInDB();
                    JOptionPane.showMessageDialog(container.frame, "Successfully Updated User");
                    container.frame.dispose();
                    new Home();
                }

                // Cancel the operation
                else
                {
                    JOptionPane.showMessageDialog(container.frame, "Operation cancelled");
                }
            }

            // Prompt the user to input information into all of the fields
            else
            {
                JOptionPane.showMessageDialog(container.frame, "Please input data into all text boxes", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Delete the supplier
        if (e.getSource() == deleteSupplier) 
        {
            // Make the user confirm their decision to delete the supplier
            int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to delete Supplier: " + supplierID  + "?", "Delete User",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

                // Confirm the deletion
                if(result == JOptionPane.YES_OPTION)
                {
                    StaticDatabaseMethods.deleteRowFromDb(supplierID, "supplier");
                    JOptionPane.showMessageDialog(container.frame, "Successfully Deleted Supplier from Database");
                    container.frame.dispose();
                    new Home();
                }

                // Cancel the operation
                else
                {
                    JOptionPane.showMessageDialog(container.frame, "Operation Cancelled");
                }
        }
    }
}
