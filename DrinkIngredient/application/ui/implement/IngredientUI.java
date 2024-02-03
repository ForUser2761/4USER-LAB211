package application.ui.implement;

import java.util.List;

import application.ui.UIInterface;
import application.validate.CommonRegex;
import application.validate.Validate;
import business_layer.entity.Ingredient;
import business_layer.service.implement.IngredientService;

public class IngredientUI implements UIInterface<Ingredient> {
    IngredientService ingredientService;

    public IngredientUI() {
        ingredientService = new IngredientService();
    }

    /**
     * Prompts the user to input an ingredient and adds it to the database.
     * If an exception occurs during the input or creation process, the error
     * message is printed to the standard error stream.
     */
    @Override
    public void input() {
        try {
            Ingredient ingredient = new Ingredient();
            // input ingredient
            ingredient.input();
            // add ingredient to database
            ingredientService.create(ingredient);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Displays all ingredients by retrieving the list of ingredients from the
     * ingredient service.
     * Prints the code, name, availability, quantity, and price of each ingredient.
     * If an exception occurs, it prints the error message.
     */
    @Override
    public void showAll() {
        try {
            List<Ingredient> listIngredients = ingredientService.findIngredientList();
            System.out.format("%-10s%-20s%-20s%-20s\n", "Code",
                    "Name", "Quantity", "Price");
            for (Ingredient ingredient : listIngredients) {
                System.out.println(ingredient);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        // enter code
        String code = Validate.getString("Enter ingredient code: ", "Code must be not empty",
                CommonRegex.INGREDIENT_CODE_REGEX);
        Ingredient ingredient = new Ingredient();
        ingredient.setCode(code);
        // confirm message
        boolean isDelete = Validate.getBoolean("Do you want to delete this ingredient? (Y/N): ", "Choice must be Y/N");
        try {
            if (isDelete) {
                // delete
                ingredientService.delete(ingredient);
            } else {
                System.out.println("Cancel delete ingredient");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }        
    }

    public void updateIngredient() {
        //Require to enter the ingredient code
        String code = Validate.getString("Enter ingredient code: ", "Code must be not empty",
                CommonRegex.INGREDIENT_CODE_REGEX);
        //If a code does not exist, the notification “The ingredient does not exist”.
        try {
            Ingredient ingredientFoundByCode = ingredientService.getById(code);
            if (ingredientFoundByCode == null) {
                System.out.println("The ingredient does not exist");
                //Otherwise, we can start inputting new information about ingredients and update.              
            } else {
                // // input new information
                // Ingredient objectInput = new Ingredient();
                // //set information of objectBeUpdated to objectInput
                // objectInput.setCode(objectBeUpdated.getCode());
                // objectInput.setName(objectBeUpdated.getName());
                // objectInput.setQuantity(objectBeUpdated.getQuantity());
                // objectInput.setPrice(objectBeUpdated.getPrice());

                //input new information
                ingredientFoundByCode.inputCanBlank();
                ingredientService.update(ingredientFoundByCode, code);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
