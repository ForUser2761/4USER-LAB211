// CategoryDAO.java
package DTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements I_File {
    public static final List<Category> categories = new ArrayList<>();

    @Override
    public void readFromFile(String fileName) {
        categories.clear(); // Clear the categories list before reading from file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String id = parts[0];
                    String name = parts[1];
                    Category category = new Category(id, name);
                    categories.add(category);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public void writeToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Category category : categories) {
                bw.write(category.getId() + "|" + category.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public List<Category> getCategories() {
        readFromFile(I_File.CATEGORY_FILE_NAME);
        return categories;
    }
}
