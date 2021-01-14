package src.AllClasses;

import javax.swing.ImageIcon;

public class Fireplace {

    private String itemId, description, itemName,image;
    private Supplier supplier;
    private int price, stock;

    public Fireplace(String itemName, int price, int stock, Supplier supplier)
    {
        this.itemId = "1";
        this.itemName = itemName;
        this.price = price;
        this.description = "Please set an item description";
        this.image = "Please set an item Image";
        this.stock = stock;
        this.supplier = supplier;
    }

    public void deleteFireplace(String id)
    {
        // Will delete fireplace from database
    }

    public void updateName(String newName)
    {
        this.itemName = newName;
    }

    public void updatePrice(int price)
    {
        this.price = price;
    }

    public void updateStock(int stockTake)
    {
        this.stock -= stockTake;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setImage(String image)
    {
        this.image = image;
    }


    public String getAllFireplaceInfo()
    {
        return String.format("Item ID:%s\nItem name:%s\nItem Price:%d\nItem Stock:%d\nItem Description:%s\nItem " +
                "Image:%s\nItem Supplier:%s",this.itemId, this.itemName, this.price, this.stock, this.description,
                this.image, this.supplier.getName());
    }

    public Supplier getSupplier()
    {
        return this.supplier;
    }

    // public String getItemId()
    // {
    //     return this.supplierID;
    // }

    // public String getLocation()
    // {
    //     return this.location;
    // }

    // public String getName()
    // {
    //     return this.name;
    // }

    // public String getContact()
    // {
    //     return this.contact;
    // }

    // public String getEmail()
    // {
    //     return this.businessEmail;
    // }

}