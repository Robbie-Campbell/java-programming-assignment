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
        Fireplace selected = DatabaseSuper.getFireplaceFromDB(5);
        System.out.println(selected.getItemName());
    }
}
