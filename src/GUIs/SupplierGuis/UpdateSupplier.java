package src.GUIs.SupplierGuis;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.AllClasses.Supplier;
import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import src.GUIs.*;
import java.io.File;
import java.io.IOException;

public class UpdateSupplier implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, updateInfoPanel;
    private JLabel image, showSupplierForUpdate, businessNamePrompt, collectionNamePrompt, namePrompt, locationPrompt,
            contactPrompt, emailPrompt;
    private JTextField nameInput, businessNameInput, collectionNameInput, locationInput, contactInput, emailInput;
    private JButton updateSupplier, deleteSupplier, updateImage;
    private int supplierID;
    private Font mainFont;
    private ImageIcon placeholder;

    // CONSTRUCTOR METHOD
    public UpdateSupplier(Supplier supplier) {

        // Create style variables
        mainFont = new Font("SansSerif", Font.BOLD, 18);

        // Extend the navbar
        supplierID = supplier.getID();
        container = new GUIContainer();
        container.frame.setTitle("Update a Supplier");
        container.frame.setBounds(100, 100, 900, 800);
        mainPanel = container.contentPanel;

        // Create the update information panel
        updateInfoPanel = new JPanel();
        updateInfoPanel.setLayout(new GridBagLayout());
        updateInfoPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, updateInfoPanel, 0, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 10);

        // Get all of the information of the supplier
        String[] selectedSupplier = StaticDatabaseMethods.getSupplierFromDB(supplier.getID()).getAllInfo();

        // Tell the user which supplier they are updating
        showSupplierForUpdate = new JLabel("UPDATING INFORMATION FOR SUPPLIER: " + selectedSupplier[0]);
        showSupplierForUpdate.setFont(mainFont);
        GUISuper.addComponent(updateInfoPanel, showSupplierForUpdate, 0, 0, 2, 1, GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL);

        // Update image button
        placeholder = new ImageIcon(selectedSupplier[7]);
        image = new JLabel();
        image.setIcon(placeholder);
        GUISuper.addComponent(updateInfoPanel, image, 1, 0, 1, 13, GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL);

        // TEMPORARY UNTIL IMAGE HAS BEEN IMPLEMENTED
        updateImage = new JButton("Update Image");
        updateImage.setFont(container.contentFont);
        updateImage.setBackground(container.updateGreen);
        updateImage.setBorder(container.raisedBorder);
        updateImage.addActionListener(this);
        GUISuper.addComponent(updateInfoPanel, updateImage, 1, 14, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        updateImage.addActionListener(this);

        // Create business name update area
        businessNamePrompt = new JLabel("Update the Business Name of the Supplier");
        businessNamePrompt.setFont(container.contentFont);
        businessNameInput = new JTextField(selectedSupplier[1]);
        GUISuper.addComponent(updateInfoPanel, businessNamePrompt, 0, 2, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, businessNameInput, 0, 3, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create name update area
        collectionNamePrompt = new JLabel("Update the Collection Name from the Supplier");
        collectionNamePrompt.setFont(container.contentFont);
        collectionNameInput = new JTextField(selectedSupplier[2]);
        GUISuper.addComponent(updateInfoPanel, collectionNamePrompt, 0, 4, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, collectionNameInput, 0, 5, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create name update area
        namePrompt = new JLabel("Update the Owner Name for this Business");
        namePrompt.setFont(container.contentFont);
        nameInput = new JTextField(selectedSupplier[3]);
        GUISuper.addComponent(updateInfoPanel, namePrompt, 0, 6, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, nameInput, 0, 7, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create location update area
        locationPrompt = new JLabel("Update the Location Base of the Supplier");
        locationInput = new JTextField(selectedSupplier[4]);
        locationPrompt.setFont(container.contentFont);
        GUISuper.addComponent(updateInfoPanel, locationPrompt, 0, 8, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, locationInput, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create contact update area
        contactPrompt = new JLabel("Update the Contact Number of the Supplier");
        contactInput = new JTextField(selectedSupplier[5]);
        contactPrompt.setFont(container.contentFont);
        GUISuper.addComponent(updateInfoPanel, contactPrompt, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, contactInput, 0, 11, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create email update area
        emailPrompt = new JLabel("Update the Business Email of the Supplier");
        emailInput = new JTextField(selectedSupplier[6]);
        emailPrompt.setFont(container.contentFont);
        emailInput.setColumns(10);
        GUISuper.addComponent(updateInfoPanel, emailPrompt, 0, 12, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, emailInput, 0, 13, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Submit an update to the db
        updateSupplier = new JButton("Update this Supplier");
        updateSupplier.setFont(container.contentFont);
        updateSupplier.setBackground(container.updateGreen);
        updateSupplier.setBorder(container.raisedBorder);
        GUISuper.addComponent(updateInfoPanel, updateSupplier, 0, 14, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        updateSupplier.addActionListener(this);

        // Delete the currently selected supplier
        deleteSupplier = new JButton("Delete this Supplier");
        deleteSupplier.setFont(container.contentFont);
        deleteSupplier.setBackground(container.dangerRed);
        deleteSupplier.setForeground(container.white);
        deleteSupplier.setBorder(container.raisedBorder);
        GUISuper.addComponent(updateInfoPanel, deleteSupplier, 0, 15, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        deleteSupplier.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Method to update the supplier in the database
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateSupplier) {

            // Create an instance of the supplier class and set all supplier information
            // from textboxes
            Supplier update = StaticDatabaseMethods.getSupplierFromDB(supplierID);
            update.setBusinessName(businessNameInput.getText());
            update.setCollectionName(collectionNameInput.getText());
            update.setName(nameInput.getText());
            update.setLocation(locationInput.getText());
            update.setContact(contactInput.getText());
            update.setEmail(emailInput.getText());

            // Make sure that the information being entered is not blank
            if (!nameInput.getText().equals("") && !locationInput.getText().equals("")
                    && !contactInput.getText().equals("") && !emailInput.getText().equals("")) {

                // Make the user confirm their decision to update the user
                int result = JOptionPane.showConfirmDialog(container.frame,
                        "Are you sure you want to update user: " + supplierID + "?", "Confirm Update",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                // Successfully complete the operation and inform the user
                if (result == JOptionPane.YES_OPTION) {
                    update.updateRowInDB();
                    JOptionPane.showMessageDialog(container.frame, "Successfully Updated User");
                    container.frame.dispose();
                    new SupplierIndex();
                }

                // Cancel the operation
                else {
                    JOptionPane.showMessageDialog(container.frame, "Operation cancelled");
                }
            }

            // Prompt the user to input information into all of the fields
            else {
                JOptionPane.showMessageDialog(container.frame, "Please input data into all text boxes",
                        "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Delete the supplier
        if (e.getSource() == deleteSupplier) {
            // Make the user confirm their decision to delete the supplier
            int result = JOptionPane.showConfirmDialog(container.frame,
                    "Are you sure you want to delete Supplier: " + supplierID + "?", "Delete User",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Confirm the deletion
            if (result == JOptionPane.YES_OPTION) {
                StaticDatabaseMethods.deleteRowFromDb(supplierID, "supplier");
                JOptionPane.showMessageDialog(container.frame, "Successfully Deleted Supplier from Database");
                container.frame.dispose();
                new SupplierIndex();
            }

            // Cancel the operation
            else {
                JOptionPane.showMessageDialog(container.frame, "Operation Cancelled");
            }
        }

        // Update the image of a supplier in the database
        if (e.getSource() == updateImage) {

            // Create a supplier object to update
            Supplier update = StaticDatabaseMethods.getSupplierFromDB(supplierID);

            // Open a file directory and set the default location to home
            final JFileChooser chooseImage = new JFileChooser();
            chooseImage.setCurrentDirectory(new File(System.getProperty("user.home")));
            int success = chooseImage.showOpenDialog(null);

            // On select run this
            if (success == JFileChooser.APPROVE_OPTION) {
                try {

                    // Get the selected image from the folder
                    File selectedFile = chooseImage.getSelectedFile();
                    BufferedImage resizedImage = ImageIO.read(selectedFile);
                    int type = resizedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : resizedImage.getType();
                    
                    // Convert input image into a resized file
                    BufferedImage resizeImageJpg = GUISuper.resizeImage(resizedImage, type, 300, 300);
                    String newPath = "src\\Images\\Suppliers\\" + supplierID + ".jpg";

                    // Save the new path into the database for that user
                    update.setImagePath(newPath);
                    update.updateRowInDB();

                    // Save the image into a local directory
                    ImageIO.write(resizeImageJpg, "jpg", new File(newPath));
                    image.setIcon(new ImageIcon(resizeImageJpg));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Hmm, that didn't work");
            }

        }
    }

}
