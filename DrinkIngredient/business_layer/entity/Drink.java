package business_layer.entity;

import java.util.HashMap;
import application.ui.implement.IngredientUI;
import application.validate.CommonRegex;
import application.validate.Validate;
import business_layer.service.implement.IngredientService;

public class Drink {
    private String code;
    private String name;
    private HashMap<String, Integer> listIngredients = new HashMap<>();
    private double price;

    public Drink() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getListIngredients() {
        return this.listIngredients;
    }

    public void addIngredient(String code, int quantity) {
        listIngredients.put(code, quantity);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-20s", code , name, price );
    }

    public void input() {
        // input code with format Dxxx, x is digit
        code = Validate.getString("Enter drink code: ",
                "The drink code should start with 'D' followed by three digits.",
                CommonRegex.DRINK_CODE_REGEX);
        // input name
        name = Validate.getString("Enter drink name: ", "Name must be not empty", CommonRegex.STRING_REGEX);
        // input price
        price = Validate.getDouble("Enter price: ", "Price must be digits", 0, Double.MAX_VALUE);
        // input list of ingredients
        IngredientUI ingredientUi = new IngredientUI();
        IngredientService ingredientService = new IngredientService();
        while (true) {
            // show list of ingredients
            ingredientUi.showAll();
            String ingredientCode = Validate.getString("Enter ingredient code: ", "Code must be not empty",
                    CommonRegex.INGREDIENT_CODE_REGEX);
            int quantity = Validate.getInteger("Enter quantity: ", "Quantity must be digits", 0, Integer.MAX_VALUE);

            // check if ingredient code is existed
            if (ingredientService.isExisted(ingredientCode)) {
                // add ingredient to list
                listIngredients.put(ingredientCode, quantity);
                if (!Validate.getBoolean("Do you want to add ingredient? (Y/N): ", "Choice must be Y/N")) {
                    break;
                }
            } else {
                System.out.println("Ingredient code is not existed.");
            }
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
