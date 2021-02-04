package src.GUIs.FireplaceGuis;

import src.AllClasses.Fireplace;
import src.AllClasses.Item;
import src.AllClasses.Supplier;

import javax.imageio.ImageIO;
import javax.swing.*;
import src.GUIs.GUIContainer;
import src.GUIs.GUISuper;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UpdateFireplace implements ActionListener {

    // INITIALISE VARIABLES
    private GUIContainer container;
    private JPanel mainPanel, updateInfoPanel;
    private JLabel image, showFireplaceForUpdate, updateFireplaceNamePrompt, priceUpdatePrompt, updateStockPrompt, updateDescriptionPrompt,
            updateStylePrompt, updateFinishPrompt;
    private JTextField updateStockInput, updateFireplaceNameInput, priceUpdateInput, updateStyleInput, updateFinishInput;
    private JButton updateFireplace, deleteFireplace, updateImage;
    private int fireplaceID;
    private Font mainFont;
    private JComboBox<String> businessNames;
    private JTextArea updateDescriptionInput;
    private BufferedImage placeholder;

    // CONSTRUCTOR METHOD
    public UpdateFireplace(Fireplace fireplace) {

        // Create style variables
        mainFont = new Font("SansSerif", Font.BOLD, 18);

        // Extend the navbar
        fireplaceID = fireplace.getId();
        container = new GUIContainer();
        container.frame.setTitle("Update a Fireplace");
        container.frame.setBounds(100, 100, 900, 800);
        mainPanel = container.contentPanel;

        // Create the update information panel
        updateInfoPanel = new JPanel();
        updateInfoPanel.setLayout(new GridBagLayout());
        updateInfoPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, updateInfoPanel, 0, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 10);

        // Get all of the information of the fireplace
        String[] selectedfireplace = new Fireplace().getFromDB(fireplace.getId()).getAllInfo();

        // Create a list of usable supplier foreign keys
        businessNames = new JComboBox<>();
        for (int supplierID : Item.getRowsFromDB(new Supplier().getItemType()))
        {
            businessNames.addItem(String.format("%d: %s", supplierID, new Supplier().getFromDB(supplierID).getBusinessName()));
        }
        businessNames.setSelectedItem("");
        GUISuper.addComponent(updateInfoPanel, businessNames, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Tell the user which fireplace they are updating
        showFireplaceForUpdate = new JLabel("UPDATING INFORMATION FOR FIREPLACE: " + selectedfireplace[0]);
        showFireplaceForUpdate.setFont(mainFont);
        GUISuper.addComponent(updateInfoPanel, showFireplaceForUpdate, 0, 0, 2, 1, GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL);

        // Update image button
        image = new JLabel();
        File oldImage = new File(selectedfireplace[6]);
        try{
                placeholder = ImageIO.read(oldImage);
                image.setIcon(new ImageIcon(placeholder));
        }
        catch (IOException ex)
        {
                ex.printStackTrace();
        }
        GUISuper.addComponent(updateInfoPanel, image, 1, 2, 1, 13, GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL);

        // TEMPORARY UNTIL IMAGE HAS BEEN IMPLEMENTED
        updateImage = new JButton("Update Image");
        updateImage.setFont(container.contentFont);
        updateImage.setBackground(container.updateGreen);
        updateImage.setBorder(container.raisedBorder);
        updateImage.addActionListener(this);
        GUISuper.addComponent(updateInfoPanel, updateImage, 1, 15, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        updateImage.addActionListener(this);

        // Create business name update area
        updateFireplaceNamePrompt = new JLabel("Update the Name of the Fireplace");
        updateFireplaceNamePrompt.setFont(container.contentFont);
        updateFireplaceNameInput = new JTextField(selectedfireplace[2]);
        GUISuper.addComponent(updateInfoPanel, updateFireplaceNamePrompt, 0, 3, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, updateFireplaceNameInput, 0, 4, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create name update area
        priceUpdatePrompt = new JLabel("Update the Price of the Fireplace");
        priceUpdatePrompt.setFont(container.contentFont);
        priceUpdateInput = new JTextField(selectedfireplace[3]);
        GUISuper.addComponent(updateInfoPanel, priceUpdatePrompt, 0, 5, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, priceUpdateInput, 0, 6, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create name update area
        updateStockPrompt = new JLabel("Update the Stock of the Fireplace");
        updateStockPrompt.setFont(container.contentFont);
        updateStockInput = new JTextField(selectedfireplace[4]);
        GUISuper.addComponent(updateInfoPanel, updateStockPrompt, 0, 7, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, updateStockInput, 0, 8, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create location update area
        updateDescriptionPrompt = new JLabel("Update the Description of the Fireplace");
        updateDescriptionInput = new JTextArea(selectedfireplace[5]);
        updateDescriptionInput.setText("\n\n");
        updateDescriptionPrompt.setFont(container.contentFont);
        GUISuper.addComponent(updateInfoPanel, updateDescriptionPrompt, 0, 9, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, updateDescriptionInput, 0, 10, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create contact update area
        updateStylePrompt = new JLabel("Update the Style of the Fireplace");
        updateStyleInput = new JTextField(selectedfireplace[7]);
        updateStylePrompt.setFont(container.contentFont);
        GUISuper.addComponent(updateInfoPanel, updateStylePrompt, 0, 11, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, updateStyleInput, 0, 12, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Create email update area
        updateFinishPrompt = new JLabel("Update the Finish of the Fireplace");
        updateFinishInput = new JTextField(selectedfireplace[8]);
        updateFinishPrompt.setFont(container.contentFont);
        updateFinishInput.setColumns(10);
        GUISuper.addComponent(updateInfoPanel, updateFinishPrompt, 0, 13, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        GUISuper.addComponent(updateInfoPanel, updateFinishInput, 0, 14, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH);

        // Submit an update to the db
        updateFireplace = new JButton("Update this fireplace");
        updateFireplace.setFont(container.contentFont);
        updateFireplace.setBackground(container.updateGreen);
        updateFireplace.setBorder(container.raisedBorder);
        GUISuper.addComponent(updateInfoPanel, updateFireplace, 0, 15, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        updateFireplace.addActionListener(this);

        // Delete the currently selected fireplace
        deleteFireplace = new JButton("Delete this fireplace");
        deleteFireplace.setFont(container.contentFont);
        deleteFireplace.setBackground(container.dangerRed);
        deleteFireplace.setForeground(container.white);
        deleteFireplace.setBorder(container.raisedBorder);
        GUISuper.addComponent(updateInfoPanel, deleteFireplace, 0, 16, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, 5);
        deleteFireplace.addActionListener(this);

        container.frame.setVisible(true);
    }

    // Method to update the fireplace in the database
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateFireplace) {

            // Create an instance of the fireplace class and set all fireplace information
            // from textboxes
            Fireplace update = new Fireplace().getFromDB(fireplaceID);
            update.setSupplierID(Integer.parseInt(businessNames.getSelectedItem().toString().split(":")[0]));
            update.setItemName(updateFireplaceNameInput.getText());
            update.setPrice(Integer.parseInt(priceUpdateInput.getText()));
            update.setTotalStock(Integer.parseInt(updateStockInput.getText()));
            update.setDescription(updateDescriptionInput.getText());
            update.setFinish(updateFinishInput.getText());
            update.setStyle(updateStyleInput.getText());

            // Make sure that the information being entered is not blank
            if (!updateFireplaceNameInput.getText().equals("") && !priceUpdateInput.getText().equals("")
                    && !updateStockInput.getText().equals("") && !updateFinishInput.getText().equals("")
                    && !updateDescriptionInput.getText().equals("") && !updateStyleInput.getText().equals("")) {

                // Make the user confirm their decision to update the user
                int result = JOptionPane.showConfirmDialog(container.frame,
                        "Are you sure you want to update Fireplace: " + fireplaceID + "?", "Confirm Update",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                // Successfully complete the operation and inform the user
                if (result == JOptionPane.YES_OPTION) {
                    update.updateRowInDB();
                    JOptionPane.showMessageDialog(container.frame, "Successfully Updated Fireplace");
                    container.frame.dispose();
                    new FireplaceIndex();
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

        // Delete the fireplace
        if (e.getSource() == deleteFireplace) {
            // Make the user confirm their decision to delete the fireplace
            int result = JOptionPane.showConfirmDialog(container.frame,
                    "Are you sure you want to delete fireplace: " + fireplaceID + "?", "Delete Fireplace",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Confirm the deletion
            if (result == JOptionPane.YES_OPTION) {
                Item.deleteRowFromDb(fireplaceID, "fireplace");
                JOptionPane.showMessageDialog(container.frame, "Successfully Deleted Fireplace from Database");
                container.frame.dispose();
                new FireplaceIndex();
            }

            // Cancel the operation
            else {
                JOptionPane.showMessageDialog(container.frame, "Operation Cancelled");
            }
        }

        // Update the image of a fireplace in the database
        if (e.getSource() == updateImage) {

            // Create a fireplace object to update
            Fireplace update = new Fireplace().getFromDB(fireplaceID);

            // Open a file directory and set the default location to home
            final JFileChooser chooseImage = new JFileChooser();
            chooseImage.setCurrentDirectory(new File(System.getProperty("user.home")));
            int success = chooseImage.showOpenDialog(null);

            // On select run this
            if (success == JFileChooser.APPROVE_OPTION) {
                try {
                    // Delete the current value in the folder
                    String oldPath = update.getImagePath();
                    if (oldPath != "src\\Images\\no-image.png")
                    {
                        new File(oldPath).delete();
                    }

                    // Get the selected image from the folder
                    File selectedFile = chooseImage.getSelectedFile();
                    BufferedImage resizedImage = ImageIO.read(selectedFile);
                    int type = resizedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : resizedImage.getType();
                    
                    // Convert input image into a resized file
                    BufferedImage resizeImageJpg = GUISuper.resizeImage(resizedImage, type, 400, 400);
                    String newPath = "src\\Images\\fireplaces\\" + fireplaceID + ".jpg";

                    // Save the new path into the database for that user
                    update.setImagePath(newPath);
                    update.updateRowInDB();

                    // Save the image into a local directory
                    ImageIO.write(resizeImageJpg, "jpg", new File(newPath));
                    image.setIcon(new ImageIcon(ImageIO.read(new File(newPath))));
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Hmm, that didn't work");
            }

        }
    }
    
}
