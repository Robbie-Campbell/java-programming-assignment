package src.UserInterfaces.FireplaceGuis;

/*
Author: Robbie Campbell
Date: 04/02/2021
Description:
This class displays all of the fireplaces in the database and allows the user to read into one or update information about a single one
*/

import javax.swing.*;

import java.awt.*;

import src.Logic.*;
import src.UserInterfaces.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class FireplaceIndex {

    private GUIContainer container;
    private JPanel mainPanel, decisionPanel;
    private JLabel businessName, fireplaceName, price, stock;

    // CONSTRUCTOR METHOD
    public FireplaceIndex() {


        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle("The Fireplace Index");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();

        // Create a scrollpane for all of the returned fireplaces from the database then append the decision panel onto it
        JScrollPane fireplaces = new JScrollPane(decisionPanel);
        decisionPanel.setLayout(new GridBagLayout());
        container.frame.setBounds(100,100,700,500);
        decisionPanel.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, fireplaces, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create indexes for the layout of fireplace
        int index = 0;
        int nextRow = 0;

        // Loop through all of the fireplace ids in the database and display them to the user
        for (int i : Item.getRowsFromDB(new Fireplace().getItemType()))
        {

            // Create a panel for each of the fireplaces information
            JPanel displayPanel = new JPanel(new GridBagLayout());
            displayPanel.setBorder(container.raisedBorder);

            // Get the fireplace information from the database in a format for displaying on labels
            String[] selected = new Fireplace().getFromDB(i).getAllInfo();

            // Resize the image for display
            try{

                // Pass in the database image
                BufferedImage resizedImage = ImageIO.read(new File(selected[6]));
                int type = resizedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : resizedImage.getType();
                
                // Resize a given image
                BufferedImage resizeImage = GUISuper.resizeImage(resizedImage, type, 150, 150);
                ImageIcon placeholder = new ImageIcon(resizeImage);
                JLabel image = new JLabel();
                image.setIcon(placeholder);
                GUISuper.addComponent(displayPanel, image, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

            // Set all label text then place in descending order onto the panel
            businessName = new JLabel("Supplier Name: " + new Supplier().getFromDB(Integer.parseInt(selected[1])).getBusinessName());
            businessName.setFont(container.contentFont);
            fireplaceName = new JLabel("Fireplace Name: " + selected[2]);
            fireplaceName.setFont(container.contentFont);
            price = new JLabel("Price: " + selected[3] + ".00");
            price.setFont(container.contentFont);
            stock = new JLabel("Stock: " + selected[4]);
            stock.setFont(container.contentFont);
            GUISuper.addComponent(displayPanel, businessName, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, fireplaceName, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, price, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(displayPanel, stock, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
            GUISuper.addComponent(decisionPanel, displayPanel, index % 2, nextRow, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 5);

            // Create a button that when pressed creates a new update information page for the user to update fireplace information
            JButton update = new JButton("Update Info");
            update.setBackground(container.updateGreen);
            update.setBorder(container.raisedBorder);
            update.setFont(container.contentFont);
            update.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    // Create an instance of the new update supplier class
                    new UpdateFireplace(new Fireplace().getFromDB(i));
                    container.frame.dispose();
                }
            });

            // Create a button that when pressed creates a new update information page for the user to update fireplace information
            JButton select = new JButton("View more");
            select.setBackground(container.confirmationBlue);
            select.setBorder(container.raisedBorder);
            select.setFont(container.contentFont);
            select.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
                {
                    // Create an instance of the new update supplier class
                    new FireplaceSingle(new Fireplace().getFromDB(i).getId());
                    container.frame.dispose();
                }
            });

            // Add the button to the panel
            GUISuper.addComponent(displayPanel, select, 0, 5, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);
            GUISuper.addComponent(displayPanel, update, 0, 6, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);
            index++;
            if (index % 2 == 0)
            {
                nextRow++;
            }
        }

        container.frame.setVisible(true);
        
    }

    // Testing
    public static void main(String[] args){
        new FireplaceIndex();
    }
}
