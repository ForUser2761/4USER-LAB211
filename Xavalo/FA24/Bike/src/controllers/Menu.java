package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
import dto.I_Menu;
import src.utils.Validate;
import java.util.ArrayList;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

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

}
