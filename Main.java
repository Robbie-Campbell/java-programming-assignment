import AllClasses.*;

public class Main {

    public static void main(String[] args){
        Fireplace newFireplace = new Fireplace("Wood Burner", 30000, 10,
                new Supplier("Joe", "Poole", "0123456789", "hello@world.com"));

        System.out.println(newFireplace.getAllFireplaceInfo());
        System.out.println(newFireplace.getSupplier().getAllSupplierInfo());
    }
}
