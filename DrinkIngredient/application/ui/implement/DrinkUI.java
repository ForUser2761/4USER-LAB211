package application.ui.implement;

import java.util.List;

import application.ui.UIInterface;
import business_layer.entity.Drink;
import business_layer.service.implement.DrinkService;

public class DrinkUI implements UIInterface<Drink>{
    DrinkService drinkService;

    public DrinkUI () {
        drinkService = new DrinkService();
    }

    @Override
    public void input() {
        try {
            //input drink
            Drink drink = new Drink();
            drink.input();
            //add drink to database
            drinkService.create(drink);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void showAll() {
        //show beverage
        try {
            List<Drink> listDrinks = drinkService.findDrinksList();
            System.out.println("List of drinks: ");
            System.out.println("-------------------------------------------------------------");
            System.out.format("%-10s %-10s %-20s\n", "Code", "Name", "Price");
            for (Drink drink : listDrinks) {
                System.out.println(drink);
            }            
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public void addDrink() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addDrink'");
    }
}
