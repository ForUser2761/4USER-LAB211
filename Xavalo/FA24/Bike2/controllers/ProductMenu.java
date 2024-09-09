package controllers;

import utils.Validate;
import java.util.ArrayList;

public class ProductMenu extends ArrayList<String> implements I_Menu {

    public ProductMenu() {
        super();
    }

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        System.out.println("Product Management System");
        System.out.println("=========================");
        for (String item : this) {
            System.out.println(item);
        }
        System.out.println("=========================");
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ",
                "Choice must be digits", 1, 10);
    }

    @Override
    public void addOptions() {
        addItem("1. Add a product");
        addItem("2. Search product by name");
        addItem("3. Update product");
        addItem("4. Delete product");
        addItem("5. Save products to file");
        addItem("6. Print list products from file");
        addItem("7. Quit");
    }

}
