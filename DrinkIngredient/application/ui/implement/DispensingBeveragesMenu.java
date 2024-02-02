package application.ui.implement;

import java.util.ArrayList;

import application.ui.IMenu;
import application.validate.Validate;

public class DispensingBeveragesMenu extends ArrayList<String> implements IMenu {

    @Override
    public void addItem(String item) {
        this.add(item);
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 2);
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
    }

    public void processBeverages() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processBeverages'");
    }

    @Override
    public void clearOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearOptions'");
    }
}
