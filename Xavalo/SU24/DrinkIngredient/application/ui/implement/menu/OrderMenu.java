package application.ui.implement.menu;

import java.util.ArrayList;

import application.ui.IMenu;
import application.ui.implement.OrderUI;
import application.validate.Validate;

public class OrderMenu extends ArrayList<String> implements IMenu {

    @Override
    public void addItem(String item) {
        this.add(item);
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 0, 2);
    }

    @Override
    public void showMenu() {
        System.out.println("3. Dispensing beverages: ");
        for (String item : this) {
            System.out.println(item);
        }
    }

    public void addOptions() {
        /**
         * 3.1. Dispensing the drink.
         * 3.2. Update the dispensing drink.
         */
        this.addItem("3.1. Dispensing the drink.");
        this.addItem("3.2. Update the dispensing drink.");
        this.addItem("0. Back to menu");
    }

    public void processBeverages() {
        OrderUI uiInterface = new OrderUI();
        addOptions();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    uiInterface.input();
                    break;
                case 2:
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
