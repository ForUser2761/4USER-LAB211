package view;

import controllers.I_Menu;
import controllers.ProductController;
import controllers.ProductMenu;

public class ProductManagement{
    public static void main(String[] args) {
        I_Menu menu = new ProductMenu();

        ProductController productController = new ProductController();
        int choice;
        boolean cont = true;

        do {
            menu.addOptions();
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    productController.add();
                    break;
                case 2:
                    // productController.searchProductByName();
                    break;
                case 3:
                    // productController.updateProduct();
                    break;
                case 4:
                    // productController.deleteProduct();
                    break;
                case 5:
                    // productController.saveProductsToFile();
                    break;
                case 6:
                    // productController.printProductsFromFile();
                    break;
                case 7:
                    cont = menu.confirmYesNo("Do you want to quit? (Y/N)");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!cont);

        System.out.println("Thank you for using Bike Store Management System!");
    }
}