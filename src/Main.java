package src;

import src.AllClasses.Fireplace;
import src.DatabaseInteractions.TableCreationScripts;

/*
Author: Robbie Campbell
Date: 14/01/2021
Description:
*/

import src.GUIs.Home;

// Run the main function, and for now testing
public class Main {

    public static void main(String[] args) {
        // new Home();
        new Fireplace(11, "Special", 10, 10).insertFireplaceIntoDB();
        
    }
}
