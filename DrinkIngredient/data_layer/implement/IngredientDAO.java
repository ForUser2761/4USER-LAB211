package data_layer.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business_layer.entity.Ingredient;
import data_layer.IGenericDAO;

public class IngredientDAO implements IGenericDAO<Ingredient> {
    private static List<Ingredient> ingredientsList = new ArrayList<>();

    public IngredientDAO() {
        try {
            loadDataFromFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the list of ingredients.
     *
     * @return the list of ingredients
     */
    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    /**
     * Loads data from a file and populates the ingredientsList.
     * The file should be in the format of code|name|isAvailable|quantity|price per line.
     * Each line is split into individual data fields and used to create Ingredient objects.
     * The Ingredient objects are then added to the ingredientsList.
     * 
     * @throws Exception if there is an error reading or closing the file.
     */
    @Override
    public void loadDataFromFile() throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(IGenericDAO.INGREDIENT_FILE_NAME); // replace with your file path
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("[|]"); // assuming data is comma-separated
                // split data
                String code = data[0].trim();
                String name = data[1].trim();
                boolean isAvailable = Boolean.parseBoolean(data[2].trim());
                int quantity = Integer.parseInt(data[3].trim());
                double price = Double.parseDouble(data[4].trim());
                // create object
                Ingredient ingredient = new Ingredient();
                ingredient.setCode(code);
                ingredient.setName(name);
                ingredient.setIsAvailable(isAvailable);
                ingredient.setQuantity(quantity);
                ingredient.setPrice(price);
                // add to list
                ingredientsList.add(ingredient);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    /**
     * Writes the list of ingredients to a file.
     * Each ingredient is written as a line in the file, with the format:
     * code | name | isAvailable | quantity | price
     * 
     * @throws Exception if an error occurs while writing to the file
     */
    @Override
    public void writeToFile() throws Exception {
        // write to file
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(IGenericDAO.INGREDIENT_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Ingredient obj : ingredientsList) {
                String line = String.format("%s | %s | %s | %s | %s",
                        obj.getCode(), obj.getName(), obj.isIsAvailable(),
                        obj.getQuantity(), obj.getPrice());
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    /**
     * Inserts an Ingredient object into the ingredientsList and writes the updated list to a file.
     * 
     * @param object the Ingredient object to be inserted
     * @throws Exception if an error occurs while inserting the object or writing to the file
     */
    @Override
    public void insert(Ingredient object) throws Exception {
        ingredientsList.add(object);
        writeToFile();
    }

    /**
     * Clears the list of ingredients.
     */
    @Override
    public void clear() {
        ingredientsList.clear();
    }

    public void delete(Ingredient object) throws Exception {
        ingredientsList.remove(object);
        writeToFile();
    }

}
