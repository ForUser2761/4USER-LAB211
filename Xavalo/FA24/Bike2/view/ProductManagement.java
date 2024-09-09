package view;

import controllers.I_Menu;
import controllers.ProductController;
import controllers.ProductMenu;

public class ProductManagement {
    public static void main(String[] args) {
        I_Menu menu = new ProductMenu();
        ProductController productController = new ProductController();

        boolean quit = false;

        menu.addOptions();
        while (!quit) {
            menu.showMenu();
            int choice = menu.getChoice();

            switch (choice) {
                case 1:
                    productController.add();
                    break;
                case 2:
                    productController.searchProductByName();
                    break;
                case 3:
                    productController.updateProduct();
                    break;
                case 4:
                    productController.deleteProduct();
                    break;
                case 5:
                    productController.saveProductsToFile();
                    break;
                case 6:
                    productController.output();
                    break;
                case 7:
                    quit = true; // Đánh dấu để thoát khỏi vòng lặp
                    System.out.println("Thank you for using Bike Store Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}