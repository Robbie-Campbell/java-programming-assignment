package src.AllClasses;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class contains all of the information for a supplier, it also contains the methods needed to return the private variables in the database class
*/


import java.sql.*;
import src.DatabaseInteractions.StaticDatabaseMethods;

public class Supplier {

    private String location, name, contact, businessEmail;
    private int ID;
    
    // CONSTRUCTOR METHOD
    public Supplier(String name, String location, String contact, String businessEmail)
    {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
    }

    // OVERRIDEN CONSTRUCTOR FOR GETTING DATA FROM A DB
    public Supplier(int ID, String name, String location, String contact, String businessEmail)
    {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
        this.ID = ID;
    }

    // GETTER METHODS FOR EACH OF THE PRIVATE VARIABLES

    // Get the location of the supplier
    public String getLocation()
    {
        return this.location;
    }

    // Get the name of the supplier
    public String getName()
    {
        return this.name;
    }

    // Get the contact of the supplier
    public String getContact()
    {
        return this.contact;
    }

    // Get the email of the supplier
    public String getEmail()
    {
        return this.businessEmail;
    }

    // Get the ID of the supplier
    public int getID()
    {
        return this.ID;
    }

    // This function returns all information in the database about this object
    public String[] getAllInfo()
    {
        return new String[]{String.valueOf(this.ID), this.name, this.location, this.contact, this.businessEmail};
    }

    // SETTER METHODS FOR EACH OF THE PRIVATE VARIABLES

    // Set the location of the supplier
    public void setLocation(String newLocation)
    {
        this.location = newLocation;
    }

    // Set the name of the supplier
    public void setName(String newName)
    {
        this.name = newName;
    }

    // Set the contact of the supplier
    public void setContact(String newContact)
    {
        this.contact = newContact;
    }

    // Set the email of the supplier
    public void setEmail(String newEmail)
    {
        this.businessEmail = newEmail;
    }

    // CREATE METHOD
    // Inserts a new supplier into the database
    public boolean insertSupplierIntoDB() {
        try {
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            String query = " insert into supplier (name, location, contact, business_email)" + " values (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.name);
            stmt.setString(2, this.location);
            stmt.setString(3, this.contact);
            stmt.setString(4, this.businessEmail);
            stmt.execute();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // UPDATE METHOD
    // update an already existing row in the supplier table with new information
    // based on the id passed into it
    public boolean updateRowInDB() {
        try {
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            String query = "UPDATE supplier SET name = ?, location = ?, contact = ?, business_email = ? where supplier_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.name);
            stmt.setString(2, this.location);
            stmt.setString(3, this.contact);
            stmt.setString(4, this.businessEmail);
            stmt.setInt(5, this.ID);
            stmt.execute();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}