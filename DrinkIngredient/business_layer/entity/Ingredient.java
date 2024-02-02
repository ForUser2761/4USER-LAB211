package business_layer.entity;

import application.validate.CommonRegex;
import application.validate.Validate;

public class Ingredient {
    private String code;
    private String name;
    private boolean isAvailable;
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

    public boolean isIsAvailable() {
        return this.isAvailable;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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
        return String.format("%-10s%-20s%-20s%-20s%-20s", code, name, isAvailable, quantity, price);
    }

    public void input() {
        code = Validate.getString("Enter ingredient code: ", "Code must be not empty",
                CommonRegex.INGREDIENT_CODE_REGEX);
        name = Validate.getString("Enter ingredient name: ", "Name must be not empty",
                CommonRegex.STRING_REGEX);
        isAvailable = Validate.getBoolean("Is available? (Y/N): ",
                "Choice must be Y or N");
        quantity = (int) Validate.getDouble("Enter quantity: ",
                "Quantity must be digits", 0, Integer.MAX_VALUE);
        price = Validate.getDouble("Enter price: ",
                "Price must be digits", 0, Double.MAX_VALUE);
    }

}
