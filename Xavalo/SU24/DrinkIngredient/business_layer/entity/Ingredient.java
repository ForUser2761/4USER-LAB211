package business_layer.entity;

import application.validate.CommonRegex;
import application.validate.Validate;

public class Ingredient {
    private String code;
    private String name;
    private int quantity;
    private double price;

    public Ingredient() {
    }

    public String getCode() {
        return code;
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

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20s%-20s", code, name, quantity, price);
    }

    public void input() {
        code = Validate.getString("Enter ingredient code: ", "Code must be not empty",
                CommonRegex.INGREDIENT_CODE_REGEX);
        name = Validate.getString("Enter ingredient name: ", "Name must be not empty",
                CommonRegex.STRING_REGEX);
        quantity = (int) Validate.getDouble("Enter quantity: ",
                "Quantity must be digits", 0, Integer.MAX_VALUE);
        price = Validate.getDouble("Enter price: ",
                "Price must be digits", 0, Double.MAX_VALUE);
    }

    public void inputCanBlank() {
        // input ingredient
        String codeInput = Validate.getString("Enter ingredient code: ", "Code must be not empty",
                CommonRegex.INGREDIENT_CODE_REGEX_BLANK);
        if (!codeInput.trim().isEmpty()) {
            code = codeInput;
        }
        String nameInput = Validate.getString("Enter ingredient name: ", "Name must be not empty",
                CommonRegex.INGREDIENT_NAME_REGEX_BLANK);
        if (!nameInput.trim().isEmpty()) {
            name = nameInput;
        }
        String quantityInput = Validate.getString("Enter quantity: ", "Quantity must be digits",
                CommonRegex.INGREDIENT_QUANTITY_REGEX_BLANK);                
        if (!quantityInput.isEmpty()) {
            quantity = Integer.parseInt(quantityInput);
        }
        String priceInput = Validate.getString("Enter price: ", "Price must be digits",
                CommonRegex.INGREDIENT_PRICE_REGEX_BLANK);
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
        }
    }


}
