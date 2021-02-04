package src.Logic;

/*
Author: Robbie Campbell
Date: 04/02/2021
Description:
This is the superclass that contains the shared attriubutes and functions of the Supplier and Fireplace classes
*/

import java.util.ArrayList;
import java.sql.*;

public abstract class Item implements ItemInterface {

    protected ItemType itemType;
    protected int ID;

    // Get all values from any table in the database
    public static ArrayList<Integer> getRowsFromDB(ItemType tableName) {
        try {

            // Create an array list to store all of the information then make a db connection
            ArrayList<Integer> listOfIds = new ArrayList<>();
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
            Statement stat = conn.createStatement();
            ResultSet rs = null;

            // Determine which query to execute
            if (tableName.equals(ItemType.SUPPLIER))
            {
                rs = stat.executeQuery("select supplier_id from supplier");
            }
            else if (tableName.equals(ItemType.FIREPLACE))
            {
                rs = stat.executeQuery("select fireplace_id from fireplace");
            }

            // Get all of the id's
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
    public static void deleteRowFromDb(int id, ItemType tableName) {
        try {
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
            String query = "";

            // Get the table name from the parameter
            if (tableName.equals(ItemType.SUPPLIER))
            {
                query = "DELETE FROM supplier where supplier_id = ?";
            }
            else if (tableName.equals(ItemType.FIREPLACE))
            {
                query = "DELETE FROM fireplace where fireplace_id = ?";
            }

            // Create statement and delete from the database
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
