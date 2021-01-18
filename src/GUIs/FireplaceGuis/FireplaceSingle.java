package src.GUIs.FireplaceGuis;

import javax.swing.*;

import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class FireplaceSingle {

    private GUIContainer container;
    private JPanel mainPanel, fireplaceInfo;

    // CONSTRUCTOR METHOD
    public FireplaceSingle(int ID) {

        // Get all information about the given fireplace
        String[] selected = StaticDatabaseMethods.getFireplaceFromDB(ID).getAllInfo();

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle(selected[3]);
        mainPanel = container.contentPanel;
        fireplaceInfo = new JPanel();

        // Set the individual fireplace layout
        fireplaceInfo.setLayout(new GridBagLayout());
        container.frame.setBounds(100,100,700,500);
        fireplaceInfo.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, fireplaceInfo, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Create an index for the layout of suppliers
        int index = 0;
        int nextRow = 0;


        // Create a panel for each of the suppliers information
        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setBorder(container.raisedBorder);


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
        JLabel businessName = new JLabel("Supplier Name: " + StaticDatabaseMethods.getSupplierFromDB(Integer.parseInt(selected[1])).getBusinessName());
        businessName.setFont(container.contentFont);
        JLabel collectionName = new JLabel("Fireplace Name: " + selected[2]);
        collectionName.setFont(container.contentFont);
        JLabel name = new JLabel("Price: " + selected[3] + ".00");
        name.setFont(container.contentFont);
        JLabel location = new JLabel("Stock: " + selected[4]);
        location.setFont(container.contentFont);
        GUISuper.addComponent(displayPanel, businessName, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(displayPanel, collectionName, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(displayPanel, name, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(displayPanel, location, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, displayPanel, index % 2, nextRow, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 5);

        // Create a button that when pressed creates a new update information page for the user to update supplier information
        JButton update = new JButton("Update Info");
        update.setBackground(container.updateGreen);
        update.setBorder(container.raisedBorder);
        update.setFont(container.contentFont);
        // update.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent e)
        //     {
        //         // Create an instance of the new update supplier class
        //         new UpdateSupplier(StaticDatabaseMethods.getSupplierFromDB(i));
        //         container.frame.dispose();
        //     }
        // });

        // Add the button to the panel
        GUISuper.addComponent(displayPanel, update, 0, 5, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);
        index++;
        if (index % 2 == 0)
        {
            nextRow++;
        }

        container.frame.setVisible(true);
        
    }

// Testing
    public static void main(String[] args){
        new FireplaceSingle(3);
    }
}
