package application.ui.implement;

import java.util.List;

import application.ui.UIInterface;
import application.validate.CommonRegex;
import application.validate.Validate;
import business_layer.entity.Drink;
import business_layer.service.implement.DrinkService;

public class DrinkUI implements UIInterface<Drink> {
    DrinkService drinkService;

    public DrinkUI() {
        drinkService = new DrinkService();
    }

    /**
     * Prompts the user to input a drink and adds it to the database.
     * If an exception occurs during the input or creation process, the error
     * message is printed to the standard error stream.
     */
    @Override
    public void input() {
        try {
            // input drink
            Drink drink = new Drink();
            drink.input();
            // add drink to database
            drinkService.create(drink);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Displays all the drinks in the system.
     */
    @Override
    public void showAll() {
        // show beverage
        try {
            List<Drink> listDrinks = drinkService.findDrinksList();
            System.out.println("List of drinks: ");
            System.out.println("-------------------------------------------------------------");
            System.out.format("%-10s %-10s %-20s\n", "Code", "Name", "Price");
            for (Drink drink : listDrinks) {
                System.out.println(drink);
            }
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Deletes a drink from the menu.
     * 
     * This method prompts the user to enter the drink code in the format Dxxx, where x is a digit.
     * If the entered code is valid, the method confirms with the user if they want to delete the drink.
     * If the user confirms, the drink is deleted using the drinkService.
     * If the user cancels the deletion, the method displays a cancellation message.
     * 
     * @throws Exception if an error occurs during the deletion process.
     */
    @Override
    public void delete() {
        // Delete the drink from the menu.
        // input drink code with format Dxxx, x is digit
        String code = Validate.getString("Enter drink code: ",
                "The drink code should start with 'D' followed by three digits.",
                CommonRegex.DRINK_CODE_REGEX);
        Drink drink = new Drink();
        drink.setCode(code);
        // Before the delete system must show, confirmed message.
        // If confirmed, the drink is deleted using the drinkService.
        // If not confirmed, the deletion is canceled.
        boolean isDelete = Validate.getBoolean("Do you want to delete this drink? (Y/N): ", "Choice must be Y/N");
        try {
            if (isDelete) {
                // delete
                drinkService.delete(drink);
                System.out.println("Delete drink successfully !!!!");
            }else {
                System.out.println("Cancel delete drink");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }        
    }

    public void updateBeverage() {
        //require enter code
        String code = Validate.getString("Enter drink code: ",
                "The drink code should start with 'D' followed by three digits.",
                CommonRegex.DRINK_CODE_REGEX);
        //If a code does not exist, the notification “The drink does not exist”.
        try {
            Drink drinkFoundByCode = drinkService.getById(code);
            if (drinkFoundByCode == null) {
                System.out.println("The drink does not exist");
                //Otherwise, we can start inputting new information about drinks and update.              
            } else {
                drinkFoundByCode.inputCanBlank();
                drinkService.update(drinkFoundByCode, code);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }        
    }
}
