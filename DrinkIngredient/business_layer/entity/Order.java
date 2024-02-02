package business_layer.entity;

import java.util.Date;

public class Order {
    private String code;
    private Drink drink;
    private int quantity;
    private Date orderTime;

    public Order() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Drink getDrink() {
        return this.drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", drink='" + getDrink() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", orderTime='" + getOrderTime() + "'" +
            "}";
    }
    
}
