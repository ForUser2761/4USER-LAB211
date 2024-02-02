package application.ui.implement;

import java.util.ArrayList;

import application.ui.IMenu;
import application.ui.UIInterface;
import application.validate.Validate;
import business_layer.entity.Ingredient;

public class ManageIngredientsMenu extends ArrayList<String> implements IMenu {

    @Override
    public void addItem(String item) {
        // TODO Auto-generated method stub
        this.add(item);
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 5);
    }

    @Override
    public void showMenu() {
        System.out.println("Manage ingredients: ");
        for (String item : this) {
            System.out.println(item);
        }
    }

    public void addOptions() {
        /**
         *  /**
         * 1.1. Add an ingredient
         * 1.2. Update ingredient information.
         * 1.3. Delete ingredient.
         * 1.4. Show all ingredients.
         *
         */
        this.addItem("1.1. Add an ingredient");
        this.addItem("1.2. Update ingredient information.");
        this.addItem("1.3. Delete ingredient.");
        this.addItem("1.4. Show all ingredients.");
        this.addItem("1.5. Back to main menu.");
    }

    public void processIngredients() {
        IngredientUI uiInterface = new IngredientUI();
        addOptions();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    // addIngredient();
                    uiInterface.input();
                    break;
                case 2:
                    // updateIngredient();
                    break;
                case 3:
                    // deleteIngredient();
                    uiInterface.delete();
                    break;
                case 4:
                    // showAllIngredients();
                    uiInterface.showAll();
                    break;
                case 5:
                    return;
            }    
        }
    }

    @Override
    public void clearOptions() {
        //clear options
        this.clear();
    }

}
