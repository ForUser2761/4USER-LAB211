package DTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements I_File {

    public static List<Product> products = new ArrayList<Product>();

    @Override
    public void readFromFile(String fileName) {
        products.clear();
        BufferedReader reader = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("\\|");
                    if (data.length == 6) {
                        String id = data[0].trim();
                        String name = data[1].trim();
                        String brandId = data[2].trim();
                        String categoryId = data[3].trim();
                        int modelYear = Integer.parseInt(data[4].trim());
                        List<Double> listPrice = new ArrayList<>();
                        String[] prices = data[5].replaceAll("[\\[\\]]", "").split(",");
                        for (String price : prices) {
                            listPrice.add(Double.parseDouble(price.trim()));
                        }
                        Product product = new Product(id, name, brandId, categoryId, modelYear, listPrice);
                        products.add(product);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void writeToFile(String fileName) {
        BufferedWriter writer = null;
        try {
            File file = new File(fileName);
            writer = new BufferedWriter(new FileWriter(file));
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
