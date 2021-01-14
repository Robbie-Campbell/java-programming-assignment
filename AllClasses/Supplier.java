package AllClasses;

public class Supplier {

    private String supplierID, location, name, contact, businessEmail;

    public Supplier(String name, String location, String contact, String businessEmail)
    {
        this.supplierID = "1";
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
    }

    public void deleteSupplier(String id)
    {
        // Will delete from database
    }

    public void deleteFireplace(String id)
    {
        // Will delete fireplace from database
    }

    public void updateName(String newName)
    {
        this.name = newName;
    }

    public void updateLocation(String newLocation)
    {
        this.location = newLocation;
    }

    public void updateContact(String newContact)
    {
        this.contact = newContact;
    }

    public void updateBusinessEmail(String newEmail)
    {
        this.businessEmail = newEmail;
    }

    public String getAllSupplierInfo()
    {
        return String.format("Supplier ID:%s\nSupplier Name:%s\nSupplier Location:%s\nSupplier Contact No:%s\n" +
                "Supplier Business Email:%s",this.supplierID, this.location, this.name, this.contact, this.businessEmail);
    }

    public String getSupplierId()
    {
        return this.supplierID;
    }

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