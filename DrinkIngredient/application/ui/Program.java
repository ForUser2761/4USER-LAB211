package application.ui;

import application.ui.implement.DispensingBeveragesMenu;
import application.ui.implement.DrinkMenu;
import application.ui.implement.ManageIngredientsMenu;
import application.validate.Validate;

public class Program {
    public static void main(String[] args) {
        DispensingBeveragesMenu beveragesMenu = new DispensingBeveragesMenu();
        DrinkMenu manageBeverageRecipesMenu = new DrinkMenu();
        ManageIngredientsMenu manageIngredientsMenu = new ManageIngredientsMenu();
        while (true) {
            System.out.println("========================Coffee Machine========================");
            System.out.println("1. Manage ingredients.");
            System.out.println("2. Manage beverage recipes.");
            System.out.println("3. Dispensing beverages.");
            System.out.println("0. Exit.");
            int option = Validate.getInteger("Enter your choice: ",
                 "Choice must be digits", 0, 3);
            switch (option) {
                case 1:
                    manageIngredientsMenu.processIngredients();
                    break;
                case 2:
                    manageBeverageRecipesMenu.processBeverageRecipes();
                    break;
                case 3:
                    beveragesMenu.processBeverages();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
