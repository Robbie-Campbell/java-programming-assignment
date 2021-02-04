package src.AllClasses;

public class Secrets {

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
    
}
