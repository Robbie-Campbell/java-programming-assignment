package src.AllClasses;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
The class which stores all of the information for the fireplace class, including the getter methouds for the private variables
*/

import java.sql.*;
import src.DatabaseInteractions.StaticDatabaseMethods;

public class Fireplace {

    private String description, itemName, image, style, finish;
    private int price, stock, supplier, ID;

    // CONSTRUCTOR METHODS
    public Fireplace(int supplier, String itemName, int price, int stock)
    {
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.supplier = supplier;
        this.description = null;
        this.image = "src\\Images\\no-image.png";
        this.style = null;
        this.finish = null;
    }

    // OVERRIDDEN CONSTRUCTOR FOR GETTING DATA FROM A DB
    public Fireplace(int ID, int supplier, String itemName, int price, int stock, String image, String description, String style, String finish)
    {
        this.ID = ID;
        this.supplier = supplier;
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
        this.style = style;
        this.finish = finish;
    }

    // GETTER METHODS FOR THE PRIVATE VARIABLES

    // Get the supplier ID
    public int getSupplierID()
    {
        return this.supplier;
    }

    // Get the fireplace name
    public String getItemName()
    {
        return this.itemName;
    }

    // Get the fireplace price
    public int getPrice()
    {
        return this.price;
    }

    // Get the current stock level of the fireplace
    public int getStock()
    {
        return this.stock;
    }

    // Get the description of the fireplace
    public String getDescription()
    {
        return this.description;
    }

    // Get the path to the image for the fireplace
    public String getImagePath()
    {
        return this.image;
    }

    // Get the style information of the fireplace
    public String getStyle()
    {
        return this.style;
    }

    // Get the path to the image for the fireplace
    public String getFinish()
    {
        return this.finish;
    }

    // Get the id of the fireplace
    public int getId()
    {
        return this.ID;
    }

    // This function returns all information in the database about this object
    public String[] getAllInfo()
    {
        return new String[]{String.valueOf(this.ID), String.valueOf(this.supplier), this.itemName, String.valueOf(this.price), 
            String.valueOf(this.stock), this.description, this.image, this.style, this.finish};
    }

    // SETTER METHODS FOR THE PRIVATE VARIABLES

    // Set a new Supplier ID
    public void setSupplierID(int newSupplier)
    {
        this.supplier = newSupplier;
    }

    // Set a new fireplace name
    public void setItemName(String newItemName)
    {
        this.itemName = newItemName;
    }

    // Set a new fireplace price
    public void setPrice(int newPrice)
    {
        this.price = newPrice;
    }

    // Remove number of stock taken
    public void setStock(int stockTaken)
    {
        this.stock -= stockTaken;
    }

    // Get the description of the fireplace
    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }

    // Get the path to the image for the fireplace
    public void setImagePath(String newImagePath)
    {
        this.image = newImagePath;
    }

    // Set the style of the fireplace
    public void setStyle(String newStyle)
    {
        this.style = newStyle;
    }

    // Set the finish of the fireplace
    public void setFinish(String newFinish)
    {
        this.finish = newFinish;
    }


    // CREATE METHOD
    // Inserts a new supplier into the database
    public void insertFireplaceIntoDB() {
        try {
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            String query = " insert into fireplace (supplier_id, item_name, price, stock, description, image, style, finish)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.supplier);
            stmt.setString(2, this.itemName);
            stmt.setInt(3, this.price);
            stmt.setInt(4, this.stock);
            stmt.setString(5, this.description);
            stmt.setString(6, this.image);
            stmt.setString(7, this.style);
            stmt.setString(8, this.finish);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // UPDATE METHODS
    // update an already existing row in the Fireplace table with new information
    // based on the id passed into it
    public void updateRowInDB() {
        try {
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            String query = "UPDATE fireplace SET supplier_id = ?, item_name = ?, price = ?, stock = ?, description = ?, image = ?, style = ?, finish = ? where fireplace_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.supplier);
            stmt.setString(2, this.itemName);
            stmt.setInt(3, this.price);
            stmt.setInt(4, this.stock);
            stmt.setString(5, this.description);
            stmt.setString(6, this.image);
            stmt.setString(7, this.style);
            stmt.setString(8, this.finish);
            stmt.setInt(9, this.ID);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Update the stock of a given fireplace
    public void updateStockLevel() {
        try {
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            String query = "UPDATE fireplace SET stock = ? WHERE fireplace_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.stock);
            stmt.setInt(2, this.ID);
            stmt.execute();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}