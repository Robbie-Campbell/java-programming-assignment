package src.GUIs.SupplierGuis;

import java.util.*;
import javax.swing.*;

import src.AllClasses.Supplier;
import src.DatabaseInteractions.StaticDatabaseMethods;

import java.awt.*;
import java.awt.event.*;
import src.GUIs.*;
import javax.swing.border.Border;

public class SupplierIndex {
    GUIContainer container;
    JPanel mainPanel, decisionPanel;

    public SupplierIndex() {
        container = new GUIContainer();
        container.frame.setTitle("The Supplier Index");
        mainPanel = container.contentPanel;
        decisionPanel = new JPanel();
        JScrollPane suppliers = new JScrollPane(decisionPanel);
        decisionPanel.setLayout(new GridBagLayout());
        decisionPanel.setBackground(new Color(230, 230, 230));
        Border raisedBorder = BorderFactory.createRaisedBevelBorder();
        GUISuper.addComponent(mainPanel, suppliers, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        int index = 0;
        for (int i : StaticDatabaseMethods.getRowsFromDB("supplier"))
        {
            JPanel displayPanel = new JPanel(new GridBagLayout());
            displayPanel.setBorder(raisedBorder);
            ImageIcon placeholder = new ImageIcon("src\\Images\\no-image.jpg");
            JLabel image = new JLabel();
            image.setIcon(placeholder);
            GUISuper.addComponent(displayPanel, image, 1, 0, 0, 4, GridBagConstraints.EAST, GridBagConstraints.VERTICAL);
            String[] selected = StaticDatabaseMethods.getSupplierFromDB(i).getAllInfo();
            JLabel name = new JLabel("Name: " + selected[1]);
            JLabel location = new JLabel("Location: " + selected[2]);
            JLabel contact = new JLabel("Contact: " + selected[3]);
            JLabel email = new JLabel("Email: " + selected[4]);
            GUISuper.addComponent(displayPanel, name, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(displayPanel, location, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(displayPanel, contact, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(displayPanel, email, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
            GUISuper.addComponent(decisionPanel, displayPanel, 0, index, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            index++;
        }

        container.frame.setVisible(true);
        
    }
}
