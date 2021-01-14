package src.DatabaseInteractions;

import java.sql.*;

public class DatabaseSuper {

    public static int countRows(String tableName)
    {
        try {
            Connection conn = DriverManager.getConnection(getDBName(), "root", "");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select count(*) from " + tableName);
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static String getDBName()
    {
        return "jdbc:mysql://localhost:3306/chesneys-fireplaces";
    }
}
