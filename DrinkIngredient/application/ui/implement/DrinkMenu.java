package application.ui.implement;

import java.util.ArrayList;

import application.ui.IMenu;
import application.validate.Validate;

public class DrinkMenu extends ArrayList<String> implements IMenu {

    @Override
    public void addItem(String item) {
        // TODO Auto-generated method stub
        this.add(item);
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 0, 4);
    }

    @Override
    public void showMenu() {
        System.out.println("2. Manage beverage recipes: ");
        for (String item : this) {
            System.out.println(item);
        }
    }

    public void addOptions() {
        /**
         * 2.1. Add the drink to menu.
         * 2.2. Update the drink information.
         * 2.3. Delete the drink from menu.
         * 2.4. Show menu.
         */
        this.addItem("2.1. Add the drink to menu.");
        this.addItem("2.2. Update the drink information.");
        this.addItem("2.3. Delete the drink from menu.");
        this.addItem("2.4. Show menu.");
        this.addItem("0. Back to main menu.");
    }

    public void processBeverageRecipes() {
        DrinkUI drinkUI = new DrinkUI();
        addOptions();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    // addBeverage();
                    drinkUI.input();
                    break;
                case 2:
                    // updateBeverage();
                    break;
                case 3:
                    // deleteBeverage();
                    break;
                case 4:
                    drinkUI.showAll();
                    break;
                case 0:
                    return;
            }
        }
    }

    @Override
    public void clearOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearOptions'");
    }

}
