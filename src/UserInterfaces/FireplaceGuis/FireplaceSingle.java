package src.UserInterfaces.FireplaceGuis;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import src.Logic.Fireplace;
import src.Logic.Supplier;
import src.UserInterfaces.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class FireplaceSingle implements ActionListener {

    private GUIContainer container;
    private JPanel mainPanel, fireplaceInfo, stockUpdatePanel;
    private JLabel businessName, fireplaceName, price, stock, takenStockPrompt, descriptionPrompt, style, finish;
    private JTextArea descriptionArea;
    private JButton update, updateStock;
    private JComboBox<Integer> stockTaken;
    private String[] selected;
    private JFrame stockUpdateFrame;
    private int id;

    // CONSTRUCTOR METHOD
    public FireplaceSingle(int id) {

        this.id = id;

        // Get all information about the given fireplace
        selected = new Fireplace().getFromDB(this.id).getAllInfo();

        // Extend the navbar
        container = new GUIContainer();
        container.frame.setTitle(selected[2]);
        mainPanel = container.contentPanel;
        fireplaceInfo = new JPanel();

        // Set the individual fireplace layout
        fireplaceInfo.setLayout(new GridBagLayout());
        container.frame.setBounds(100,100,700,500);
        fireplaceInfo.setBackground(new Color(230, 230, 230));
        GUISuper.addComponent(mainPanel, fireplaceInfo, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 20);

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
            GUISuper.addComponent(fireplaceInfo, image, 1, 1, 1, 5, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }


        // Set all label text 
        businessName = new JLabel("Supplier Name: " + new Supplier().getFromDB(Integer.parseInt(selected[1])).getBusinessName());
        businessName.setFont(container.titleFont);
        fireplaceName = new JLabel("Fireplace Name: " + selected[2]);
        fireplaceName.setFont(container.contentFont);
        price = new JLabel("Price: " + selected[3] + ".00");
        price.setFont(container.contentFont);
        stock = new JLabel("Stock: " + selected[4]);
        stock.setFont(container.contentFont);
        finish = new JLabel("Finish: " + selected[8]);
        finish.setFont(container.contentFont);
        style = new JLabel("Style: " + selected[7]);
        style.setFont(container.contentFont);
        descriptionPrompt = new JLabel("Description:");
        descriptionPrompt.setFont(container.contentFont);
        descriptionArea = new JTextArea();
        descriptionArea.setFont(container.contentFont);
        if (selected[5] == null)
        {
            descriptionArea.setText("No description available");
        }
        else
        {
            descriptionArea.setText(selected[5]);
        }
        descriptionArea.setEditable(false);
        GUISuper.addComponent(fireplaceInfo, businessName, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, fireplaceName, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, price, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, stock, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, finish, 0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, style, 0, 6, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, descriptionPrompt, 0, 7, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);
        GUISuper.addComponent(fireplaceInfo, descriptionArea, 0, 8, 2, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 5);

        // Create a button that when pressed creates a new update information page for the user to update supplier information
        update = new JButton("Update Stock");
        update.setBackground(container.updateGreen);
        update.setBorder(container.raisedBorder);
        update.setFont(container.contentFont);
        update.addActionListener(this);

        // Add the button to the panel
        GUISuper.addComponent(fireplaceInfo, update, 0, 11, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);

        container.frame.setVisible(true);      
    }

    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == update)
        {
            stockUpdateFrame = new JFrame("Stock taken");
            stockUpdateFrame.setBounds(150,150,300,200);
            stockUpdatePanel = new JPanel(new GridBagLayout());
            stockUpdateFrame.add(stockUpdatePanel);
            takenStockPrompt = new JLabel("Select how many items have been taken");
            updateStock = new JButton("Update the Stock Level");
            updateStock.addActionListener(this);
            stockTaken = new JComboBox<>();
            for (int i = 1; i < Integer.parseInt(selected[4]) + 1; i++)
            {
                stockTaken.addItem(i);
            }

            // Add a combobox for stock taken
            GUISuper.addComponent(stockUpdatePanel, takenStockPrompt, 0, 0, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);
            GUISuper.addComponent(stockUpdatePanel, stockTaken, 0, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);
            GUISuper.addComponent(stockUpdatePanel, updateStock, 0, 2, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3);

            stockUpdateFrame.setVisible(true);
        }

        if (e.getSource() == updateStock)
        {
            Fireplace updateStock = new Fireplace().getFromDB(id);
            updateStock.setStock((int) stockTaken.getSelectedItem());
            updateStock.updateStockLevel();
            stockUpdateFrame.dispose();
            JOptionPane.showMessageDialog(container.frame, "Stock has Successfully been Updated!");
            stock.setText("Stock: " + updateStock.getStock());
        }

    }

// Testing
    public static void main(String[] args){
        new FireplaceSingle(5);
    }
}
