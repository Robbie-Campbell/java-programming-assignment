package src.DatabaseInteractions;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
Converts the information from the fireplace class into a format that can be submitted into a database
*/

import java.sql.*;
import src.AllClasses.Fireplace;

public class FireplaceDatabase {

    private String description, itemName, image;
    private int price, stock, supplier;

    // CONSTRUCTOR METHOD
    // Set all variables according to fireplace parameter using the instance getter
    // methods
    public FireplaceDatabase(Fireplace fireplace) {
        this.itemName = fireplace.getItemName();
        this.price = fireplace.getPrice();
        this.stock = fireplace.getStock();
        this.description = fireplace.getDescription();
        this.image = fireplace.getImagePath();
        this.supplier = fireplace.getSupplierID();
    }

    // CREATE METHOD
    // Inserts a new supplier into the database
    public boolean insertFireplaceIntoDB() {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), DatabaseSuper.getUsername(), DatabaseSuper.getPass());
            String query = " insert into fireplace (supplier_id, item_name, price, stock, description, image)" + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.supplier);
            stmt.setString(2, this.itemName);
            stmt.setInt(3, this.price);
            stmt.setInt(4, this.stock);
            stmt.setString(5, this.description);
            stmt.setString(6, this.image);
            stmt.execute();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
