package src.TableScripts;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class offers a list of all of the scripts used to make the database, these can be called from as a method to create each table
*/

import java.sql.*;

import src.Logic.*;

public class TableCreationScripts {

    // Creates the supplier table in the database
    public static void createSupplierTable() {
        try {
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(),
                    Secrets.getPass());
            Statement stat = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS `chesneys-fireplaces`.`supplier` ( "
                    + "`supplier_id` INT NOT NULL AUTO_INCREMENT, " + "`business_name` VARCHAR(45) NOT NULL, "
                    + "`collection_name` VARCHAR(45) NOT NULL, " + "`owner_name` VARCHAR(30) NOT NULL, "
                    + "`location` VARCHAR(45) NOT NULL, " + "`contact` VARCHAR(45) NOT NULL, "
                    + "`business_email` VARCHAR(45) NOT NULL, " + "`image` VARCHAR(45) NOT NULL, "
                    + "PRIMARY KEY (`supplier_id`)); ";
            stat.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Create the table fireplace table in the database
    public static void createFireplaceTable() {
        try {
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(),
                    Secrets.getPass());
            Statement stat = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS `chesneys-fireplaces`.`fireplace` ("
                    + "`fireplace_id` INT NOT NULL AUTO_INCREMENT," + "`supplier_id` INT NOT NULL,"
                    + "`item_name` VARCHAR(45) NOT NULL," + "`price` INT NOT NULL," + "`stock` INT NOT NULL,"
                    + "`description` VARCHAR(100) NULL," + "`image` VARCHAR(45) NULL,"
                    + "`style` VARCHAR(45) NULL," + "`finish` VARCHAR(45) NULL," + "PRIMARY KEY (fireplace_id),"
                    + "FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON DELETE CASCADE);";
            stat.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
