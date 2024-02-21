package business_layer.entity;

import java.util.HashMap;

import application.ui.implement.IngredientUI;
import application.validate.CommonRegex;
import application.validate.Validate;
import business_layer.service.implement.IngredientService;

public class Drink {
    private String code;
    private String name;
    /**
     * A map of ingredients and their quantities required to make the drink.
     * Key: ingredient code
     * Value: quantity
     */
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

    public void setListIngredients(HashMap<String, Integer> listIngredients) {
        this.listIngredients = listIngredients;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-20s", code, name, price);
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

    public void displayListIngredients() {
        System.out.println("List of ingredients: ");
        System.out.println("-------------------------------------------------------------");
        System.out.format("%-10s %-10s\n", "Code", "Quantity");
        for (String code : listIngredients.keySet()) {
            System.out.format("%-10s %-10s\n", code, listIngredients.get(code));
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void inputCanBlank() {
        // input code
        String codeInput = Validate.getString("Enter new drink code: ",
                "The drink code should start with 'D' followed by three digits.",
                CommonRegex.DRINK_CODE_REGEX_BLANK);
        if (!codeInput.isEmpty()) {
            code = codeInput;
        }
        // input name
        String nameInput = Validate.getString("Enter drink name: ",
                "Name must be not empty",
                CommonRegex.DRINK_NAME_REGEX_BLANK);
        if (!nameInput.isEmpty()) {
            name = nameInput;
        }
        // input price
        String priceInput = Validate.getString("Enter price: ",
                "Price must be digits",
                CommonRegex.DRINK_PRICE_REGEX_BLANK);
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
        }
        // input list of ingredients
        IngredientUI ingredientUi = new IngredientUI();
        IngredientService ingredientService = new IngredientService();
        // show list of ingredients
        displayListIngredients();
        // ask want to edit or adding more ingredient to list
        if (Validate.getBoolean("Do you want to edit ingredients list? (Y/N): ",
                "Choice must be Y/N")) {
            // show list of ingredients
            while (true) {
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

    }

}
