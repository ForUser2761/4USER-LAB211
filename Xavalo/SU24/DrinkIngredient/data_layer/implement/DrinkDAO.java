package data_layer.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business_layer.entity.Drink;
import data_layer.IGenericDAO;

public class DrinkDAO implements IGenericDAO<Drink> {
    private static List<Drink> drinkList = new ArrayList<>();

    /**
     * Constructs a new DrinkDAO object.
     * It loads data from a file and handles any exceptions that occur during the
     * process.
     */
    public DrinkDAO() {
        try {
            loadDataFromFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the list of drinks.
     *
     * @return the list of drinks
     */
    public List<Drink> getDrinkList() {
        return drinkList;
    }

    /**
     * Loads data from a file and populates the drinkList with Drink objects.
     * The file should be in the format: code|name|price|ingredientCode1 -
     * quantity,ingredientCode2 - quantity,ingredientCode3 - quantity
     * Each line in the file represents a single Drink object.
     * 
     * @throws Exception if an error occurs while reading the file or parsing the
     *                   data.
     */
    @Override
    public void loadDataFromFile() throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(IGenericDAO.DRINK_FILE_NAME); // replace with your file path
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Drink drink = new Drink();
                // read in format code|name|price|ingredientCode1 - quantity,ingredientCode2
                // - quantity,ingredientCode3 - quantity
                String[] parts = line.split("\\|");
                // code
                String code = parts[0].trim();
                // name
                String name = parts[1].trim();
                // price
                double price = Double.parseDouble(parts[2].trim());
                // list of ingredients
                String[] ingredientParts = parts[3].split(",");
                for (String ingredientPart : ingredientParts) {
                    String[] ingredient = ingredientPart.split(":");
                    // add to list of ingredients
                    drink.addIngredient(ingredient[0].trim(), Integer.parseInt(ingredient[1].trim()));
                }
                // create drink object and add to list
                drink.setCode(code);
                drink.setName(name);
                drink.setPrice(price);
                drinkList.add(drink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the drink data to a file.
     * The data is written in the format of code|name|price|ingredientCode1 -
     * quantity,ingredientCode2 - quantity,ingredientCode3 - quantity.
     * 
     * @throws Exception if an error occurs while writing to the file.
     */
    @Override
    public void writeToFile() throws Exception {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(IGenericDAO.DRINK_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Drink drink : drinkList) {
                // write in format of code|name|price|ingredientCode1 - quantity,ingredientCode2
                // - quantity,ingredientCode3 - quantity

                String line = drink.getCode() + "|" + drink.getName() + "|" + drink.getPrice() + "|";
                HashMap<String, Integer> ingredients = drink.getListIngredients();
                for (String key : ingredients.keySet()) {
                    line += key + ":" + ingredients.get(key) + ",";
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                bufferedWriter.close();
                fileWriter.close();
            }
        }
    }

    /**
     * Inserts a Drink object into the drinkList and writes the updated list to a
     * file.
     *
     * @param t the Drink object to be inserted
     * @throws Exception if an error occurs while inserting the Drink object or
     *                   writing to the file
     */
    @Override
    public void insert(Drink t) throws Exception {
        drinkList.add(t);
        writeToFile();
    }

    /**
     * Clears the drink list.
     */
    @Override
    public void clear() {
        drinkList.clear();
    }

    /**
     * Deletes a Drink object from the drinkList and writes the updated list to a file.
     *
     * @param object the Drink object to be deleted
     * @throws Exception if an error occurs while deleting the object or writing to the file
     */
    public void delete(Drink object) throws Exception {
        drinkList.remove(object);
        writeToFile();
    }

}
