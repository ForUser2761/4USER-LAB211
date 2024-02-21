package application.ui.implement;

import java.util.List;

import application.ui.UIInterface;
import application.validate.CommonRegex;
import application.validate.Validate;
import business_layer.entity.Drink;
import business_layer.entity.Order;
import business_layer.service.implement.DrinkService;
import business_layer.service.implement.IngredientService;
import business_layer.service.implement.OrderService;

public class OrderUI implements UIInterface<Order> {
    IngredientService ingredientService;
    DrinkService drinkService;
    OrderService orderService;

    public OrderUI() {
        ingredientService = new IngredientService();
        drinkService = new DrinkService();
        orderService = new OrderService();
    }

    @Override
    public void input() {
        try {
            // display drink list
            displayDrinkList();
            // input drink code
            String codeInput = Validate.getString("Enter new drink code: ",
                    "The drink code should start with 'D' followed by three digits.",
                    CommonRegex.DRINK_CODE_REGEX_BLANK);
            // get drink by code
            Drink drinkFoundByCode = drinkService.getById(codeInput);
            // checking drink's ingredient quantity out of stock ?
            boolean isOutOfStock = false;
            int drinkQuantity = 1;
            if (drinkFoundByCode != null) {
                // input quantity of drink
                drinkQuantity = Validate.getInteger("Enter quantity: ",
                        "The quantity should be a positive integer.",
                        1, Integer.MAX_VALUE);
                isOutOfStock = ingredientService.isOutOfStock(drinkFoundByCode, drinkQuantity);
            }
            // if drink is not exist or out of stock, throw exception
            if (drinkFoundByCode == null || isOutOfStock) {
                throw new Exception("Drink is not exist or out of stock");
            }
            // create order
            Order order = new Order();
            order.setDrinkCode(codeInput);
            order.setQuantity(drinkQuantity);
            // add order to database
            orderService.create(order);
            //update ingredient quantity
            ingredientService.updateIngredientQuantity(drinkFoundByCode, drinkQuantity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void displayDrinkList() throws Exception {
        List<Drink> listDrinks = drinkService.findDrinksList();
        System.out.println("List of drinks: ");
        System.out.println("-------------------------------------------------------------");
        System.out.format("%-10s %-10s %-20s\n", "Code", "Name", "Price");
        for (Drink drink : listDrinks) {
            System.out.println(drink);
        }
        System.out.println("-------------------------------------------------------------");
    }

    @Override
    public void showAll() {
        try {
            List<Order> listOrders = orderService.findOrdersList();
            System.out.println("List of orders: ");
            System.out.println("-------------------------------------------------------------");
            System.out.format("%-10s %-10s %-20s\n", "Code", "Drink Code", "Quantity");
            for (Order order : listOrders) {
                System.out.println(order);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
