package src.DatabaseInteractions;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
This class contains all of the static functions that are shared between more than one of the tables in the database.
*/

import java.sql.*;
import java.util.ArrayList;
import src.AllClasses.*;

public class StaticDatabaseMethods {

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
    // Connect with the database and select a single supplier based on id, return it as the Supplier class
    public static Supplier getSupplierFromDB(int id) {
        try {
            Supplier wantedSupplier = null;
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            PreparedStatement stat = conn.prepareStatement("select * from supplier where supplier_id = ?");
            stat.setInt(1, id);
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

    // Connect with the database and select a single supplier based on id, return it as the Fireplace class
    public static Fireplace getFireplaceFromDB(int id) {
        try {
            Fireplace wantedFireplace = null;
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            PreparedStatement stat = conn.prepareStatement("select * from fireplace where fireplace_id = ?");
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                wantedFireplace = new Fireplace(rs.getInt("fireplace_id"), rs.getInt("supplier_id"), rs.getString("item_name"), rs.getInt("price"), rs.getInt("stock"), rs.getString("image"), rs.getString("description"));
            }
            conn.close();
            return wantedFireplace;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    // Get all values from any table in the database
    public static ArrayList<Integer> getRowsFromDB(String tableName) {
        try {
            ArrayList<Integer> listOfIds = new ArrayList<>();
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
            Statement stat = conn.createStatement();
            ResultSet rs = null;
            if (tableName.equals("supplier"))
            {
                rs = stat.executeQuery("select supplier_id from supplier");
            }
            else if (tableName.equals("fireplace"))
            {
                rs = stat.executeQuery("select fireplace_id from fireplace");
            }
            while (rs.next()) {
                listOfIds.add(rs.getInt(1));
            }
            conn.close();
            return listOfIds;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    // DELETE METHOD
    // update an already existing row in the supplier table with new information
    // based on the id passed into it
    public static boolean deleteRowFromDb(int id, String tableName) {
        try {
            Connection conn = DriverManager.getConnection(StaticDatabaseMethods.getDBName(), StaticDatabaseMethods.getUsername(), StaticDatabaseMethods.getPass());
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
            stmt.execute();
            conn.close();
            System.out.println("User successfully deleted");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
