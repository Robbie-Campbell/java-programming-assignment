package src.DatabaseInteractions;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class contains all of the static functions that are shared between more than one of the tables in the database.
*/

import java.sql.*;
import java.util.Scanner;

public class DatabaseSuper {

    // CONNECTION CONFIG
    // The database path
    public static String getDBName()
    {
        return "jdbc:mysql://localhost:3306/chesneys-fireplaces";
    }

    // The username for the database
    public static String getUsername()
    {
        return "root";
    }

    // The password for the database
    public static String getPass()
    {
        return "";
    }

    // STATIC METHODS
    // Connect with the database and select a single supplier based on id
    public static boolean getSupplierFromDB(int id, String tableName) {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), DatabaseSuper.getUsername(), DatabaseSuper.getPass());
            PreparedStatement stat = null;
            if (tableName.equals("supplier"))
            {
                stat = conn.prepareStatement("select name from supplier where supplier_id = ?");
            }
            else if (tableName.equals("fireplace"))
            {
                stat = conn.prepareStatement("select name from fireplace where firepace_id = ?");
            }
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Override the get supplier method and return all of the suppliers in the table
    public static boolean getSupplierFromDB(String tableName) {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), DatabaseSuper.getUsername(), DatabaseSuper.getPass());
            Statement stat = conn.createStatement();
            ResultSet rs = null;
            if (tableName.equals("supplier"))
            {
                rs = stat.executeQuery("select * from supplier");
            }
            else if (tableName.equals("fireplace"))
            {
                rs = stat.executeQuery("select * from fireplace");
            }
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
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
    public static boolean updateRowInDB(int id, String tableName, String column, String newValue) {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), DatabaseSuper.getUsername(), DatabaseSuper.getPass());
            String query = "";
            if (tableName.equals("supplier"))
            {
                switch(column)
                {
                    case "name":
                        query = "UPDATE supplier SET name = ? WHERE supplier_id = ?;";
                        break;
                    case "location":
                        query = "UPDATE supplier SET location = ? WHERE supplier_id = ?;";
                        break;
                    case "contact":
                        query = "UPDATE supplier SET contact = ? WHERE supplier_id = ?;";
                        break;
                    case "business_email":
                        query = "UPDATE supplier SET business_email = ? WHERE supplier_id = ?;";
                        break;
                }
            }
            if (tableName.equals("fireplace"))
            {
                switch(column)
                {
                    case "supplier_id":
                        query = "UPDATE fireplace SET supplier_id = ? WHERE fireplace_id = ?;";
                        break;
                    case "item_name":
                        query = "UPDATE fireplace SET location = ? WHERE fireplace_id = ?;";
                        break;
                    case "price":
                        query = "UPDATE fireplace SET price = ? WHERE fireplace_id = ?;";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, Integer.parseInt(newValue));
                        stmt.setInt(2, id);
                        stmt.execute();
                        conn.close();
                        return true;
                    case "stock":
                        query = "UPDATE fireplace SET stock = ? WHERE fireplace_id = ?;";
                        PreparedStatement stat = conn.prepareStatement(query);
                        stat.setInt(1, Integer.parseInt(newValue));
                        stat.setInt(2, id);
                        stat.execute();
                        conn.close();
                        return true;
                    case "description":
                        query = "UPDATE fireplace SET description = ? WHERE fireplace_id = ?;";
                        break;
                    case "image":
                        query = "UPDATE fireplace SET image = ? WHERE fireplace_id = ?;";
                        break;
                }
            }
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newValue);
            stmt.setInt(2, id);
            stmt.execute();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // DELETE METHOD
    // update an already existing row in the supplier table with new information
    // based on the id passed into it
    public static boolean deleteRowFromDb(int id, String tableName) {
        try {
            Connection conn = DriverManager.getConnection(DatabaseSuper.getDBName(), DatabaseSuper.getUsername(), DatabaseSuper.getPass());
            String query = "";
            if (tableName.equals("supplier"))
            {
                query = "DELETE FROM supplier where supplier_id = ?";
            }
            else if (tableName.equals("fireplace"))
            {
                query = "DELETE FROM fireplace where fireplace_id = ?";
            }
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            // Check that the user wants to delete the supplier from the table
            System.out.println("Are you sure you want to delete " + tableName + " " + String.valueOf(id) + "?(y/n):");
            Scanner confirm = new Scanner(System.in);
            String option = confirm.nextLine();

            // Delete the user
            if (option.equals("y")) {
                stmt.execute();
                conn.close();
                System.out.println("User successfully deleted");
                return true;
            }

            // Cancel the operation
            else {
                System.out.println("Operation has been cancelled");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
