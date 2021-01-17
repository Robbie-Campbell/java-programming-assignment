package src.GUIs.SupplierGuis;

/***********
 * 
 * THIS CODE IS CURRENTLY UNNECESSARY
 * 
 ***********/

import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;
import javax.swing.*;

public class DeleteSupplier implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, decisionPanel;
    private JLabel idPrompt, name, location, contact, email;
    private JComboBox<Integer> listOfIds;
    private JButton deleteSupplier;

    // CONSTRUCTOR METHOD
    public DeleteSupplier() {

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("Update a Supplier");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, decisionPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        idPrompt = new JLabel("Select an ID to Delete from Database");
        GUISuper.addComponent(decisionPanel, idPrompt, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create a combobox for a list of ids to delete
        listOfIds = new JComboBox<>();
        for (int i : StaticDatabaseMethods.getRowsFromDB("supplier")) {
            listOfIds.addItem(i);
        }
        listOfIds.setSelectedItem(null);
        listOfIds.addActionListener(this);
        GUISuper.addComponent(decisionPanel, listOfIds, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Insert empty labels to fill when id is selected
        name = new JLabel(" ");
        location = new JLabel(" ");
        contact = new JLabel(" ");
        email = new JLabel(" ");

        // Add them all to the main panel
        GUISuper.addComponent(decisionPanel, name, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, location, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, contact, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, email, 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Submit an update to the db
        deleteSupplier = new JButton("Delete this Supplier");
        GUISuper.addComponent(decisionPanel, deleteSupplier, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        deleteSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        // Update the content of all of the text areas
        if (e.getSource() == listOfIds) {
            int getSupplierInfo = (int) listOfIds.getSelectedItem();
            String[] selected = StaticDatabaseMethods.getSupplierFromDB(getSupplierInfo).getAllInfo();
            name.setText("Supplier Name: " + selected[1]);
            location.setText("Supplier Location: " + selected[2]);
            contact.setText("Supplier Contact No: " + selected[3]);
            email.setText("Supplier Email: " + selected[4]);
        }

        if (e.getSource() == deleteSupplier) 
        {
            int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to delete Supplier: " + listOfIds.getSelectedItem()  + "?", "Delete User",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION)
                {
                    StaticDatabaseMethods.deleteRowFromDb((int) listOfIds.getSelectedItem(), "supplier");
                    JOptionPane.showMessageDialog(container.frame, "Successfully Deleted Supplier from Database");
                    container.frame.dispose();
                    new Home();
                }
                else
                {
                    JOptionPane.showMessageDialog(container.frame, "Operation Cancelled");
                }
        }
    }
}