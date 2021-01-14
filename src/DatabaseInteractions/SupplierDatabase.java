
package src.DatabaseInteractions;
/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class unifies the Supplier class with the database. When the Supplier instance is passed into the SupplierDatabase instance.
*/

import java.sql.*;
import src.AllClasses.Supplier;

public class SupplierDatabase {

    // All variables available from the Supplier class
    private String location, name, contact, businessEmail;
    private static String query;

    // CONSTRUCTOR METHOD
    // Set all variables according to supplier parameter using the instance getter
    // methods
    public SupplierDatabase(Supplier supplier) {
        this.name = supplier.getName();
        this.location = supplier.getLocation();
        this.contact = supplier.getContact();
        this.businessEmail = supplier.getEmail();
    }

    // CREATE METHOD
    // Inserts a new supplier into the database
    public boolean insertSupplierIntoDB() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), DatabaseSuper.getUsername(), DatabaseSuper.getPass());
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
}
