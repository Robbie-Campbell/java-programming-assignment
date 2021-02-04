package src.AllClasses;

import java.util.ArrayList;
import java.sql.*;

public abstract class Item {

    // ABSTRACT METHODS
    // Create abstract method for insertion to be overriden
    public abstract void insertIntoDB();

    // Create abstract method for updating to be written
    public abstract void updateRowInDB();

    // Create abstract method for retrieving supplier from db
    public abstract Item getFromDB(int id);

    // Create abstract method for returning all information
    public abstract String[] getAllInfo();

    // Create abstract method for getting the type of class
    public abstract ItemType getItemType();

    // Create abstract method for returning item type

    // Get all values from any table in the database
    public static ArrayList<Integer> getRowsFromDB(String tableName) {
        try {

            // Create an array list to store all of the information then make a db connection
            ArrayList<Integer> listOfIds = new ArrayList<>();
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
            Statement stat = conn.createStatement();
            ResultSet rs = null;

            // Determine which query to execute
            if (tableName.equals("supplier"))
            {
                rs = stat.executeQuery("select supplier_id from supplier");
            }
            else if (tableName.equals("fireplace"))
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
    public static boolean deleteRowFromDb(int id, String tableName) {
        try {
            Connection conn = DriverManager.getConnection(Secrets.getDBName(), Secrets.getUsername(), Secrets.getPass());
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
