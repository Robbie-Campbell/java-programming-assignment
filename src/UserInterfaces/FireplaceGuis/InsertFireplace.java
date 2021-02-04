package src.UserInterfaces.FireplaceGuis;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import src.Logic.*;
import src.UserInterfaces.*;

public class InsertFireplace implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, insertFireplacePanel;
    private JComboBox<String> businessNames;
    private JLabel itemNamePrompt, pricePrompt, stockPrompt;
    private JTextField itemNameInput, priceInput, stockInput;
    private JButton insertFireplace;

    // CONSTRUCTOR METHOD
    public InsertFireplace() {

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("Create New Fireplace");
        mainPanel = container.contentPanel;

        // Create the insert supplier panel
        insertFireplacePanel = new JPanel();
        insertFireplacePanel.setLayout(new GridBagLayout());
        insertFireplacePanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, insertFireplacePanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 20);

        // Create a list of usable supplier foreign keys
        businessNames = new JComboBox<>();
        for (int supplierID : Item.getRowsFromDB(new Supplier().getItemType()))
        {
            businessNames.addItem(String.format("%d: %s", supplierID, new Supplier().getFromDB(supplierID).getBusinessName()));
        }
        businessNames.setSelectedItem("");
        GUISuper.addComponent(insertFireplacePanel, businessNames, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create name input area
        itemNamePrompt = new JLabel("Insert the Name of the Fireplace");
        itemNamePrompt.setFont(container.contentFont);
        itemNameInput = new JTextField();
        GUISuper.addComponent(insertFireplacePanel, itemNamePrompt, 0, 3, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertFireplacePanel, itemNameInput, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create name input area
        pricePrompt = new JLabel("Insert the Price of the Fireplace");
        pricePrompt.setFont(container.contentFont);
        priceInput = new JTextField();
        GUISuper.addComponent(insertFireplacePanel, pricePrompt, 0, 5, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertFireplacePanel, priceInput, 0, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create name input area
        stockPrompt = new JLabel("Insert the Stock of the Fireplace");
        stockPrompt.setFont(container.contentFont);
        stockInput = new JTextField();
        GUISuper.addComponent(insertFireplacePanel, stockPrompt, 0, 7, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        GUISuper.addComponent(insertFireplacePanel, stockInput, 0, 8, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);


        // Submit an supplier to the db
        insertFireplace = new JButton("Create Fireplace");
        insertFireplace.setBackground(container.confirmationBlue);
        insertFireplace.setBorder(container.raisedBorder);
        insertFireplace.setFont(container.contentFont);
        GUISuper.addComponent(insertFireplacePanel, insertFireplace, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);
        insertFireplace.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Method to add a new supplier to the database
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == insertFireplace) 
        {

            try{
                // Create an instance of the supplier class with the textbox information
                Fireplace insert = new Fireplace(Integer.parseInt(businessNames.getSelectedItem().toString().split(":")[0]), itemNameInput.getText(), Integer.parseInt(priceInput.getText()), Integer.parseInt(stockInput.getText()));

                // Check to make sure that all textboxes are not empty
                if (!itemNameInput.getText().equals("") && !priceInput.getText().equals("") && !stockInput.getText().equals(""))
                {

                    // Make the user confirm their decision to add a new supplier
                    int result = JOptionPane.showConfirmDialog(container.frame, "Are you sure you want to create the Fireplace: " + itemNameInput.getText() + "?", "Create User",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

                    // Inform the user of the success of the insert
                    if(result == JOptionPane.YES_OPTION)
                    {
                        insert.insertIntoDB();
                        JOptionPane.showMessageDialog(container.frame, "Successfully Created Fireplace!");
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
            catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(container.frame, "Only insert Integer values into the price and stock fields", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
