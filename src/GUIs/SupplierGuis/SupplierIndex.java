package src.GUIs.SupplierGuis;

import javax.swing.*;

import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;


public class SupplierIndex {

    private GUIContainer container;
    private JPanel mainPanel, decisionPanel;

    // CONSTRUCTOR METHOD
    public SupplierIndex() {


        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("The Supplier Index");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();

        // Create a scrollpane for all of the returned suppliers from the database then append the decision panel onto it
        JScrollPane suppliers = new JScrollPane(decisionPanel);
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, suppliers, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create an index for the layout of suppliers
        int index = 0;

        // Loop through all of the supplier ids in the database and display them to the user
        for (int i : StaticDatabaseMethods.getRowsFromDB("supplier"))
        {

            // Create a panel for each of the suppliers information
            JPanel displayPanel = new JPanel(new GridBagLayout());
            displayPanel.setBorder(container.raisedBorder);

            // TEMPORARY UNTIL IMAGE HAS BEEN IMPLEMENTED
            ImageIcon placeholder = new ImageIcon("src\\Images\\no-image.jpg");
            JLabel image = new JLabel();
            image.setIcon(placeholder);
            GUISuper.addComponent(displayPanel, image, 1, 0, 0, 3, GridBagConstraints.EAST, GridBagConstraints.VERTICAL);

            // Get the supplier information from the database in a format for displaying on labels
            String[] selected = StaticDatabaseMethods.getSupplierFromDB(i).getAllInfo();

            // Set all label text then place in descending order onto the panel
            JLabel name = new JLabel("Name: " + selected[1]);
            name.setFont(container.contentFont);
            JLabel location = new JLabel("Location: " + selected[2]);
            location.setFont(container.contentFont);
            JLabel contact = new JLabel("Contact: " + selected[3]);
            contact.setFont(container.contentFont);
            JLabel email = new JLabel("Email: " + selected[4]);
            email.setFont(container.contentFont);
            GUISuper.addComponent(displayPanel, name, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(displayPanel, location, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(displayPanel, contact, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(displayPanel, email, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(decisionPanel, displayPanel, 0, index, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

            // Create a button that when pressed creates a new update information page for the user to update supplier information
            JButton update = new JButton("Update Info");
            update.setBackground(container.updateGreen);
            update.setBorder(container.raisedBorder);
            update.setFont(container.contentFont);
            update.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {

                    // Create an instance of the new update supplier class
                    new UpdateSupplier(StaticDatabaseMethods.getSupplierFromDB(i));
                    container.frame.dispose();
                }
            });

            // Add the button to the panel
            GUISuper.addComponent(displayPanel, update, 1, 3, 0, 1, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 15, 5);
            index++;
        }

        container.frame.setVisible(true);
        
    }

    // Testing
    public static void main(String[] args){
        new SupplierIndex();
    }
}
