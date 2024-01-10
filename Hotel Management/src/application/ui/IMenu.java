/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.ui;

/**
 *
 * @author ADMIN
 */
public interface IMenu {

    /**
     * add a menu item --> add text to menu
     *
     * @param item
     */
    void addItem(String item);

    /**
     * get user choice (user input their choice)
     *
     * @return user choice
     */
    int getChoice();

    /**
     * show menu for user choice
     */
    void showMenu();

}
