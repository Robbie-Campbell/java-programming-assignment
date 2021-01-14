package src;

import src.AllClasses.*;
import src.DatabaseInteractions.*;

public class Main {

    public static void main(String[] args){
        SupplierDatabaseInteraction checkForJoe  = new SupplierDatabaseInteraction("Rab", "Hello", "No", "hello@world.com");
        checkForJoe.insertSupplierIntoDB();
    }
}
