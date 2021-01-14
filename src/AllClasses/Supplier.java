package src.AllClasses;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class contains all of the information for a supplier, it also contains the methods needed to return the private variables in the database class
*/

public class Supplier {

    private String location, name, contact, businessEmail;
    
    // CONSTRUCTOR METHOD
    public Supplier(String name, String location, String contact, String businessEmail)
    {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
    }

    // GETTER METHODS FOR EACH OF THE PRIVATE VARIABLES
    public String getLocation()
    {
        return this.location;
    }

    public String getName()
    {
        return this.name;
    }

    public String getContact()
    {
        return this.contact;
    }

    public String getEmail()
    {
        return this.businessEmail;
    }
}