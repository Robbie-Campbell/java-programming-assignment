package src.DatabaseInteractions;

import java.sql.*;

import src.AllClasses.Supplier;

public class SupplierDatabaseInteraction extends Supplier {

    private String location, name, contact, businessEmail;
    private int supplierID;

    public SupplierDatabaseInteraction(String name, String location, String contact, String businessEmail) {
        super(name, location, contact, businessEmail);
        this.supplierID = DatabaseSuper.countRows("supplier");
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.businessEmail = businessEmail;
    }

    public void getSupplierFromDB() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), "root", "");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select name from supplier where supplier_id = " + this.supplierID);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertSupplierIntoDB()
    {
        try{
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), "root", "");
            String query = " insert into supplier (supplier_id, name, location, contact, business_email)"
        + " values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.supplierID);
            stmt.setString(2, this.name);
            stmt.setString(3, this.location);
            stmt.setString(4, this.contact);
            stmt.setString(5, this.businessEmail);
            stmt.execute();
            conn.close();
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }

    // insert into supplier values (1, "Joe", "Bournemouth", "0123456789",
    // "hello@world.com")

}
