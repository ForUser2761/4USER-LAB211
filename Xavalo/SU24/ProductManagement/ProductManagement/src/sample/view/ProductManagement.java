package sample.view;

import sample.dto.I_List;
import sample.dto.I_Menu;
import sample.controllers.Menu;
import sample.controllers.ProductList;
import sample.utils.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
public class ProductManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. Add new product");
        menu.addItem("2. Remove a product");
        menu.addItem("3. Update a product");
        menu.addItem("4. Sort list");
        menu.addItem("5. Remove by code");
        menu.addItem("5. Remove by code 2");

        menu.addItem("7: Quit");
        int choice;
        boolean cont = false;
        I_List list = new ProductList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.add();
                    list.output();
                    break;
                case 2:
                    list.remove();
                    list.output();
                    break;
                case 3:
                    list.update();
                    list.output();
                    break;
                case 4:
                    list.sort();
                    list.output();
                    break;
                case 5:
                    String code = Utils.getString("Input code: ");
                    list.remove(code);
                    list.output();
                    break;
                case 6:
                    code = Utils.getString("Input code: ");
                    list.remove(code);
                    list.output();
                    break;
                case 7:
                    cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;
                case 8:
                    list.output2();
                    break;
            }
        } while (!cont);
    }
}
