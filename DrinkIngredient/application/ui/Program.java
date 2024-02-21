package application.ui;

import application.ui.implement.OrderMenu;
import application.ui.implement.DrinkMenu;
import application.ui.implement.IngredientsMenu;
import application.validate.Validate;

public class Program {
    public static void main(String[] args) {
        OrderMenu beveragesMenu = new OrderMenu();
        DrinkMenu manageBeverageRecipesMenu = new DrinkMenu();
        IngredientsMenu manageIngredientsMenu = new IngredientsMenu();
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
