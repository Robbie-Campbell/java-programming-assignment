package src.GUIs.SupplierGuis;

import javax.swing.*;

import src.AllClasses.Item;
import src.AllClasses.Supplier;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


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
        container.frame.setBounds(100,100,700,500);
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, suppliers, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create an index for the layout of suppliers
        int index = 0;

        // Loop through all of the supplier ids in the database and display them to the user
        for (int i : Item.getRowsFromDB(new Supplier().getItemType()))
        {

            // Create a panel for each of the suppliers information
            JPanel displayPanel = new JPanel(new GridBagLayout());
            displayPanel.setBorder(container.raisedBorder);

            // Get the supplier information from the database in a format for displaying on labels
            String[] selected = new Supplier().getFromDB(i).getAllInfo();

            // Resize the image for display
            try{

                // Pass in the database image
                BufferedImage resizedImage = ImageIO.read(new File(selected[7]));
                int type = resizedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : resizedImage.getType();
                
                // Resize a given image
                BufferedImage resizeImage = GUISuper.resizeImage(resizedImage, type, 200, 200);
                ImageIcon placeholder = new ImageIcon(resizeImage);
                JLabel image = new JLabel();
                image.setIcon(placeholder);
                GUISuper.addComponent(displayPanel, image, 1, 0, 0, 4, GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }


            // Set all label text then place in descending order onto the panel
            JLabel businessName = new JLabel("Business Name: " + selected[1]);
            businessName.setFont(container.contentFont);
            JLabel collectionName = new JLabel("Collection Name: " + selected[2]);
            collectionName.setFont(container.contentFont);
            JLabel name = new JLabel("Owner Name: " + selected[3]);
            name.setFont(container.contentFont);
            JLabel location = new JLabel("Location: " + selected[4]);
            location.setFont(container.contentFont);
            JLabel contact = new JLabel("Contact: " + selected[5]);
            contact.setFont(container.contentFont);
            JLabel email = new JLabel("Email: " + selected[6]);
            email.setFont(container.contentFont);
            GUISuper.addComponent(displayPanel, businessName, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, collectionName, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, name, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, location, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, contact, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, email, 0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(decisionPanel, displayPanel, 0, index, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 5);

            // Create a button that when pressed creates a new update information page for the user to update supplier information
            JButton update = new JButton("Update Info");
            update.setBackground(container.updateGreen);
            update.setBorder(container.raisedBorder);
            update.setFont(container.contentFont);
            update.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    // Create an instance of the new update supplier class
                    new UpdateSupplier(new Supplier().getFromDB(i));
                    container.frame.dispose();
                }
            });

            // Add the button to the panel
            GUISuper.addComponent(displayPanel, update, 1, 4, 0, 1, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 3);
            index++;
        }

        container.frame.setVisible(true);
        
    }

    // Testing
    public static void main(String[] args){
        new SupplierIndex();
    }
}
