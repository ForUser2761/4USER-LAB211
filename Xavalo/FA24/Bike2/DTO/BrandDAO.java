// BrandDAO.java
package DTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO implements I_File {

    public static List<Brand> brands = new ArrayList<>();

    public BrandDAO() {
        readFromFile(I_File.BRAND_FILE_NAME);
    }

    @Override
    public void readFromFile(String fileName) {
        brands.clear(); // Clear the brands list before reading from file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String id = parts[0];
                    String name = parts[1];
                    Brand brand = new Brand(id, name);
                    brands.add(brand);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public void writeToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Brand brand : brands) {
                bw.write(brand.getId() + "|" + brand.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public List<Brand> getBrands() {
        return brands;
    }
}
