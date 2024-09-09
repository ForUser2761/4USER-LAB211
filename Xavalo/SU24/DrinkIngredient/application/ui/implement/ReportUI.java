package application.ui.implement;

import java.util.List;
import java.util.Map;
import business_layer.entity.Drink;
import business_layer.entity.Ingredient;
import business_layer.entity.Order;
import business_layer.service.implement.DrinkService;
import business_layer.service.implement.IngredientService;
import business_layer.service.implement.OrderService;

public class ReportUI {
    IngredientService ingredientService;
    DrinkService drinkService;
    OrderService orderService;

    public ReportUI() {
        ingredientService = new IngredientService();
        drinkService = new DrinkService();
        orderService = new OrderService();
    }

    public void showIngredientAvailable() {
        try {
            List<Ingredient> ingredients = ingredientService.findIngredientList();
            System.out.println("The ingredients are available: ");
            System.out.format("%-10s%-20s%-20s%-20s\n", "Code",
                    "Name", "Quantity", "Price");
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getQuantity() > 0) {
                    System.out.println(ingredient);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showOutOfStockDrink() {
        try {
            List<Ingredient> ingredients = ingredientService.findIngredientList();
            List<Drink> drinks = drinkService.findDrinksList();
            System.out.println("The drinks for which the store is out of ingredients: ");
            System.out.format("%-10s%-20s%\n", "Code",
                    "Name");
            // check out of stock drink

            for (Drink drink : drinks) {
                boolean isOutOfStock = false;
                // check each ingredient of drink
                for (Map.Entry<String, Integer> entry : drink.getListIngredients().entrySet()) {
                    // key
                    String ingredientCode = entry.getKey();
                    // value
                    int quantity = entry.getValue();
                    for (Ingredient ingredient : ingredients) {
                        if (ingredientCode.equalsIgnoreCase(ingredient.getCode())) {
                            if (ingredient.getQuantity() < quantity) {
                                isOutOfStock = true;
                                break;
                            }
                        }
                    }
                }
                if (isOutOfStock) {
                    System.out.println(drink);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showDispensingDrink() {
        try {
            List<Order> listOrder = orderService.findOrdersList();
            //sorted by name    
            listOrder.sort((o1, o2) -> {
                return o1.getDrinkCode().compareTo(o2.getDrinkCode());
            });
            System.out.println("List of orders: ");
            System.out.println("-------------------------------------------------------------");
            System.out.format("%-10s %-10s %-20s\n", "Code", "Drink Code", "Quantity");
            for (Order order : listOrder) {
                System.out.println(order);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
