package controllers;

import java.util.ArrayList;
import java.util.List;

import DTO.Brand;
import DTO.BrandDAO;
import DTO.Category;
import DTO.CategoryDAO;
import DTO.Product;
import DTO.ProductDAO;
import utils.Validate;

public class ProductController implements I_List {
    private final ProductDAO productDAO = new ProductDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public int find(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public void add() {
        while (true) {

            String name = Validate.getString("Enter product name: ", "Product name cannot be empty.",
                    "^(?!\\s*$).+");

            String brandId = chooseBrand();
            String categoryId = chooseCategory();

            int modelYear = Validate.getInteger("Enter production year: ", "Invalid production year.", 1900, 2100);

            List<Double> listPrice = new ArrayList<>();
            boolean continueAdding = true;
            while (continueAdding) {
                double price = Validate.getDouble("Enter selling price (positive number): ", "Invalid selling price.",
                        0.0,
                        Double.MAX_VALUE);
                listPrice.add(price);
                continueAdding = Validate.getBoolean("Do you want to add another selling price? (y/n): ",
                        "Please enter y or n.");
            }

            Product newProduct = new Product(name, brandId, categoryId, modelYear, listPrice);
            productDAO.getProducts().add(newProduct);
            productDAO.writeToFile("products.txt");

            System.out.println("New product has been added successfully!");
            if (Validate.getBoolean("Do you want to return to the main menu? (y/n): ", "Please enter y or n.")) {
                break;
            }
        }
    }

    private String chooseBrand() {
        List<Brand> brands = brandDAO.getBrands();
        System.out.println("Choose a brand:");
        for (int i = 0; i < brands.size(); i++) {
            Brand brand = brands.get(i);
            System.out.println((i + 1) + ". " + brand.getName() + " (ID: " + brand.getId() + ")");
        }

        int choice = Validate.getInteger("Enter the number of the brand: ", "Invalid choice.", 1, brands.size());
        return brands.get(choice - 1).getId();
    }

    private String chooseCategory() {
        List<Category> categories = categoryDAO.getCategories();
        System.out.println("Choose a category:");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            System.out.println((i + 1) + ". " + category.getName() + " (ID: " + category.getId() + ")");
        }

        int choice = Validate.getInteger("Enter the number of the category: ", "Invalid choice.", 1,
                categories.size());
        return categories.get(choice - 1).getId();
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public void output() {
        // check list product empty
        if (productDAO.getProducts().isEmpty()) {
            System.out.println("No product found.");
            return;
        }
        // display format
        System.out.println(String.format("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s", "ID", "Name", "Brand ID",
                "Category ID", "Model Year", "List Price"));
        // display product
        productDAO.getProducts().forEach(System.out::println);
    }

    public void searchProductByName() {
        String searchString = Validate.getString("Enter a search string (a part of product name): ",
                "Search string cannot be empty.", "^(?!\\s*$).+");

        List<Product> searchResults = new ArrayList<>();
        for (Product product : productDAO.getProducts()) {
            if (product.getName().toLowerCase().contains(searchString.toLowerCase())) {
                searchResults.add(product);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("Have no any Product");
        } else {
            System.out.println(String.format("%-15s | %-15s | %-15s | %-15s | %-15s | %-15s", "ID", "Name", "Brand ID",
                    "Category ID", "Model Year", "List Price"));
            searchResults.stream()
                    .sorted((p1, p2) -> Integer.compare(p1.getModelYear(), p2.getModelYear()))
                    .forEach(System.out::println);
        }

        if (Validate.getBoolean("Do you want to return to the main menu? (y/n): ", "Please enter y or n.")) {
            return;
        } else {
            searchProductByName();
        }
    }

}
