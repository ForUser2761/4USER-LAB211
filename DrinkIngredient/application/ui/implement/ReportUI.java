package application.ui.implement;

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
        
    }
}
