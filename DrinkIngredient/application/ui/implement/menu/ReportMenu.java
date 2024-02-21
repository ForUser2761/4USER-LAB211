package application.ui.implement.menu;

import java.util.ArrayList;

import application.ui.IMenu;
import application.ui.implement.ReportUI;
import application.validate.Validate;

public class ReportMenu extends ArrayList<String> implements IMenu  {

    @Override
    public void addItem(String item) {
        this.add(item);
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 0, 3);
    }

    @Override
    public void showMenu() {
        System.out.println("==============Report==============");
        for (String item : this) {
            System.out.println(item);
        }
    }

    @Override
    public void addOptions() {
        this.addItem("1. The ingredients are available.");
        this.addItem("2. The drinks for which the store is out of ingredients.");
        this.addItem("3. Show all the dispensing drink.");        
        this.addItem("0. Back to menu");
    }

    @Override
    public void clearOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearOptions'");
    }

    public void processReport() {
        ReportUI reportUI = new ReportUI();
        addOptions();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    // show ingredient available;
                    reportUI.showIngredientAvailable();
                    break;
                case 2:
                    // showOutOfIngredients();
                    break;
                case 3:
                    // showDispensingDrink();
                    break;
                case 0:
                    return;
            }
        }
    }
    
}
