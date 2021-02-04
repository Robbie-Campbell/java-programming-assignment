package src.AllClasses;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class contains all of the information for a supplier, it also contains the methods needed to return the private variables in the database class
*/


import java.sql.*;

public class Supplier extends Item implements ItemInterface {

    private ItemType itemType = ItemType.SUPPLIER;
    private String location, name, contact, businessEmail, collectionName, businessName, imagePath;
    private int ID;
    
    // CONSTRUCTOR METHOD
    public Supplier(String businessName, String collectionName, String ownerName,  String location, String contact, String businessEmail, String supplierImage)
    {
        this.collectionName = collectionName;
        this.businessName = businessName;
        this.name = ownerName;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
        this.imagePath = supplierImage;
    }

    // OVERRIDEN CONSTRUCTOR FOR GETTING DATA FROM A DB
    public Supplier(int ID, String businessName, String collectionName, String ownerName, String location, String contact, String businessEmail, String supplierImage)
    {
        this.ID = ID;
        this.collectionName = collectionName;
        this.businessName = businessName;
        this.name = ownerName;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
        this.imagePath = supplierImage;
    }

        // Constructor for getting db info
    public Supplier(){};

    // GETTER METHODS FOR EACH OF THE PRIVATE VARIABLES

    // Get the ID of the supplier
    public int getID()
    {
        return this.ID;
    }

    // Get the business name of the supplier
    public String getBusinessName()
    {
        return this.businessName;
    }

    // Get the collection range of the supplier
    public String getCollectionName()
    {
        return this.collectionName;
    }

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

    // Get the imagePath of the supplier
    public String getImagePath()
    {
        return this.imagePath;
    }

    // This function returns all information in the database about this object
    @Override
    public String[] getAllInfo()
    {
        return new String[]{String.valueOf(this.ID), this.businessName, this.collectionName, this.name, this.location, this.contact, this.businessEmail, this.imagePath};
    }

    // Get the itemtype of this class
    @Override
    public ItemType getItemType() {
        return this.itemType;
    }

    // SETTER METHODS FOR EACH OF THE PRIVATE VARIABLES

    // Set the business name of the supplier
    public void setBusinessName(String newBusinessName)
    {
        this.businessName = newBusinessName;
    }
    // Set the collection of the supplier
    public void setCollectionName(String newCollectionName)
    {
        this.collectionName = newCollectionName;
    }

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

    // Set the email of the supplier
    public void setImagePath(String newPath)
    {
        this.imagePath = newPath;
    }

    // CREATE METHOD
    // Inserts a new supplier into the database
    @Override
    public void insertIntoDB() {
        try {
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
            String query = " insert into supplier (business_name, collection_name, owner_name, location, contact, business_email, image) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.businessName);
            stmt.setString(2, this.collectionName);
            stmt.setString(3, this.name);
            stmt.setString(4, this.location);
            stmt.setString(5, this.contact);
            stmt.setString(6, this.businessEmail);
            stmt.setString(7, this.imagePath);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // UPDATE METHOD
    // update an already existing row in the supplier table with new information
    // based on the id passed into it
    @Override
    public void updateRowInDB() {
        try {
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
            String query = "UPDATE supplier SET business_name = ?, collection_name = ?, owner_name = ?, location = ?, contact = ?, business_email = ?, image = ? where supplier_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.businessName);
            stmt.setString(2, this.collectionName);
            stmt.setString(3, this.name);
            stmt.setString(4, this.location);
            stmt.setString(5, this.contact);
            stmt.setString(6, this.businessEmail);
            stmt.setString(7, this.imagePath);
            stmt.setInt(8, this.ID);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Connect with the database and select a single supplier based on id, return it as the Supplier class
    @Override
    public Supplier getFromDB(int id) {
        try {

            // Create the database connection and make the statement
            Supplier wantedSupplier = null;

            // Connect to the database and prepare the statement for execution
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
            PreparedStatement stat = conn.prepareStatement("select * from supplier where supplier_id = ?");
            stat.setInt(1, id);

            // Get all of the information about one of the suppliers and store it in a supplier object
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                wantedSupplier = new Supplier(rs.getInt("supplier_id"), rs.getString("business_name"), rs.getString("collection_name"), rs.getString("owner_name"), 
                rs.getString("location"), rs.getString("contact"), rs.getString("business_email"), rs.getString("image"));
            }
            conn.close();
            return wantedSupplier;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}