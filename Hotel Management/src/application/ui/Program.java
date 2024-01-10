package application.ui;

import application.ui.implement.Menu;

public class Program {

    /**
     * The main method is the entry point of the program. It creates a Menu
     * object, adds options to the menu, and processes the hotel menu.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        //add options
        menu.addOptions();

        //process menu
        menu.processHotel();
    }
}
