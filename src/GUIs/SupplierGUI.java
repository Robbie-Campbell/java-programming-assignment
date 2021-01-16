package src.GUIs;

import java.util.*;
import javax.swing.*;

import com.mysql.cj.protocol.Warning;

import src.AllClasses.Supplier;
import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;

public class SupplierGUI implements ActionListener {
    GUIContainer container;
    JPanel mainPanel, decisionPanel;
    JLabel idPrompt, namePrompt, locationPrompt, contactPrompt, emailPrompt;
    JTextField nameInput, locationInput, contactInput, emailInput;
    JComboBox listOfIds;
    JButton updateSupplier;

    public SupplierGUI() {
        container = new GUIContainer();
        container.frame.setBounds(100, 100, 400, 400);
        container.frame.setTitle("Suppliers");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, decisionPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        idPrompt = new JLabel("Select an ID to update");
        GUISuper.addComponent(decisionPanel, idPrompt, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create a combobox for a list of ids to update
        listOfIds = new JComboBox();
        for (int i : StaticDatabaseMethods.getRowsFromDB("supplier")) {
            listOfIds.addItem(i);
        }
        listOfIds.setSelectedItem(null);
        listOfIds.addActionListener(this);
        GUISuper.addComponent(decisionPanel, listOfIds, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create name update area
        namePrompt = new JLabel("Update the name of this person");
        nameInput = new JTextField();
        nameInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, namePrompt, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, nameInput, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create location update area
        locationPrompt = new JLabel("Update the location of this person");
        locationInput = new JTextField();
        locationInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, locationPrompt, 0, 4, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, locationInput, 0, 5, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create contact update area
        contactPrompt = new JLabel("Update the contact number of this person");
        contactInput = new JTextField();
        contactInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, contactPrompt, 0, 6, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, contactInput, 0, 7, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create email update area
        emailPrompt = new JLabel("Update the email of this person");
        emailInput = new JTextField();
        emailInput.setColumns(10);
        GUISuper.addComponent(decisionPanel, emailPrompt, 0, 8, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(decisionPanel, emailInput, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Submit an update to the db
        updateSupplier = new JButton("Update this supplier information");
        GUISuper.addComponent(decisionPanel, updateSupplier, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        updateSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Open up new pages
    public void actionPerformed(ActionEvent e) {

        // Update the content of all of the text areas
        if (e.getSource() == listOfIds) {
            int getSupplierInfo = (int) listOfIds.getSelectedItem();
            String[] selected = StaticDatabaseMethods.getSupplierFromDB(getSupplierInfo).getAllInfo();
            nameInput.setText(selected[1]);
            locationInput.setText(selected[2]);
            contactInput.setText(selected[3]);
            emailInput.setText(selected[4]);
        }

        if (e.getSource() == updateSupplier) 
        {
            int getSupplierInfo = (int) listOfIds.getSelectedItem();
            Supplier update = StaticDatabaseMethods.getSupplierFromDB(getSupplierInfo);
            update.setName(nameInput.getText());
            update.setLocation(locationInput.getText());
            update.setContact(contactInput.getText());
            update.setEmail(emailInput.getText());
            if (!nameInput.getText().equals("") && !locationInput.getText().equals("") && !contactInput.getText().equals("") && !emailInput.getText().equals(""))
            {
                int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to update user: " + getSupplierInfo + "?", "Swing Tester",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION)
                {
                update.updateRowInDB();
                JOptionPane.showMessageDialog(container.frame, "Successfully updated user");
                }
                else
                {
                    JOptionPane.showMessageDialog(container.frame, "Operation cancelled");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(container.frame, "Please input data into all text boxes", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
