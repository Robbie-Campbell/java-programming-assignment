package src.AllClasses;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
The class which stores all of the information for the fireplace class, including the getter methouds for the private variables
*/

public class Fireplace {

    private String description, itemName, image;
    private int price, stock, supplier, ID;

    // CONSTRUCTOR METHODS
    public Fireplace(int supplier, String itemName, int price, int stock)
    {
        this.itemName = itemName;
        this.price = price;
        this.description = null;
        this.image = null;
        this.stock = stock;
        this.supplier = supplier;
    }

    // OVERRIDEN CONSTRUCTOR FOR GETTING DATA FROM A DB
    public Fireplace(int ID, int supplier, String itemName, int price, int stock, String image, String description)
    {
        this.ID = ID;
        this.supplier = supplier;
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
    }

    // GETTER METHODS FOR THE PRIVATE VARIABLES
    public int getSupplierID()
    {
        return this.supplier;
    }
    public String getItemName()
    {
        return this.itemName;
    }

    public int getPrice()
    {
        return this.price;
    }

    public int getStock()
    {
        return this.stock;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getImagePath()
    {
        return this.image;
    }

}