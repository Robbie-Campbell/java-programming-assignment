package src;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
*/

import src.AllClasses.*;
import src.DatabaseInteractions.*;

// Run the main function, and for now testing
public class Main {

    public static void main(String[] args) {
        Fireplace selected = StaticDatabaseMethods.getFireplaceFromDB(5);
        selected.setDescription("This is a new description");
        selected.setImagePath("image");
        selected.updateDBRow();

        Supplier rab = StaticDatabaseMethods.getSupplierFromDB(3);
        System.out.println(rab.getAllInfo()[1]);
    }
}
