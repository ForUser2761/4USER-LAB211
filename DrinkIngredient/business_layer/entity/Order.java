package business_layer.entity;


public class Order {
    private String code;
    private String drinkCode;
    private int quantity;

    public Order() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDrinkCode() {
        return drinkCode;
    }

    public void setDrinkCode(String drinkCode) {
        this.drinkCode = drinkCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-20s", code, drinkCode, quantity);
    }
    
}
