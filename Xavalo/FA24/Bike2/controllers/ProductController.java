package controllers;

import java.util.ArrayList;
import java.util.List;

import DTO.Brand;
import DTO.BrandDAO;
import DTO.Category;
import DTO.CategoryDAO;
import DTO.I_File;
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

    public void saveProductsToFile() {
        productDAO.writeToFile(I_File.PRODUCT_FILE_NAME);
        System.out.println("Products have been saved to file successfully!");
    }

    public void deleteProduct() {
        String productId = Validate.getString("Enter the product ID: ", "Product ID cannot be empty.", "^(?!\\s*$).+");

        Product productToDelete = null;
        for (Product product : productDAO.getProducts()) {
            if (product.getId().equals(productId)) {
                productToDelete = product;
                break;
            }
        }

        if (productToDelete == null) {
            System.out.println("Product does not exist");
        } else {
            if (Validate.getBoolean("Are you sure you want to delete this product? (y/n): ", "Please enter y or n.")) {
                productDAO.getProducts().remove(productToDelete);
                productDAO.writeToFile(I_File.PRODUCT_FILE_NAME); // Lưu dữ liệu mới vào file
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product deletion canceled.");
            }
        }

        if (Validate.getBoolean("Do you want to return to the main menu? (y/n): ", "Please enter y or n.")) {
            return;
        } else {
            deleteProduct();
        }
    }

    public void updateProduct() {
        String productId = Validate.getString("Enter the product ID: ", "Product ID cannot be empty.", "^(?!\\s*$).+");

        Product productToUpdate = null;
        for (Product product : productDAO.getProducts()) {
            if (product.getId().equals(productId)) {
                productToUpdate = product;
                break;
            }
        }

        if (productToUpdate == null) {
            System.out.println("Product does not exist");
        } else {
            String newName = Validate.getString("Enter new product name (leave blank to keep current name): ",
                    "Invalid product name.", "^(?:\\s*|.+)$");
            if (!newName.isEmpty()) {
                productToUpdate.setName(newName);
            }

            String newBrandId = chooseBrand("Enter new brand ID (leave blank to keep current brand): ");
            if (newBrandId != null) {
                productToUpdate.setBrandId(newBrandId);
            }

            String newCategoryId = chooseCategory("Enter new category ID (leave blank to keep current category): ");
            if (newCategoryId != null) {
                productToUpdate.setCategoryId(newCategoryId);
            }

            int newModelYear = Validate.getInteger("Enter new model year (leave blank to keep current year): ",
                    "Invalid model year.", 1900, 2100, productToUpdate.getModelYear());
            productToUpdate.setModelYear(newModelYear);

            List<Double> newListPrice = updateListPrice(productToUpdate.getListPrice());
            productToUpdate.setListPrice(newListPrice);

            productDAO.writeToFile(I_File.PRODUCT_FILE_NAME);
            System.out.println("Product updated successfully!");
        }

        if (Validate.getBoolean("Do you want to return to the main menu? (y/n): ", "Please enter y or n.")) {
            return;
        } else {
            updateProduct();
        }
    }

    private List<Double> updateListPrice(List<Double> currentListPrice) {
        List<Double> newListPrice = new ArrayList<>(currentListPrice);
        boolean continueAdding = true;

        System.out.println("Current list price: " + currentListPrice);
        System.out.println("Enter new list price (leave blank to keep current list price):");

        while (continueAdding) {
            double newPrice = Validate.getDouble("Enter selling price (positive number or leave blank to stop): ",
                    "Invalid selling price.", 0.0, Double.MAX_VALUE, -1.0);

            if (newPrice == -1.0) {
                break;
            }

            newListPrice.add(newPrice);
            continueAdding = Validate.getBoolean("Do you want to add another selling price? (y/n): ",
                    "Please enter y or n.");
        }

        if (newListPrice.isEmpty()) {
            System.out.println("Keeping current list price.");
            return currentListPrice;
        } else {
            return newListPrice;
        }
    }

    private String chooseBrand(String prompt) {
        List<Brand> brands = brandDAO.getBrands();
        System.out.println(prompt);
        System.out.println("Choose a brand:");
        for (int i = 0; i < brands.size(); i++) {
            Brand brand = brands.get(i);
            System.out.println((i + 1) + ". " + brand.getName() + " (ID: " + brand.getId() + ")");
        }
        System.out.println((brands.size() + 1) + ". Keep current brand");

        int choice = Validate.getInteger("Enter the number of the brand: ", "Invalid choice.", 1, brands.size() + 1);
        if (choice == brands.size() + 1) {
            return null; // Giữ nguyên thương hiệu hiện tại
        } else {
            return brands.get(choice - 1).getId();
        }
    }

    private String chooseCategory(String prompt) {
        List<Category> categories = categoryDAO.getCategories();
        System.out.println(prompt);
        System.out.println("Choose a category:");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            System.out.println((i + 1) + ". " + category.getName() + " (ID: " + category.getId() + ")");
        }
        System.out.println((categories.size() + 1) + ". Keep current category");

        int choice = Validate.getInteger("Enter the number of the category: ", "Invalid choice.", 1,
                categories.size() + 1);
        if (choice == categories.size() + 1) {
            return null; // Giữ nguyên danh mục hiện tại
        } else {
            return categories.get(choice - 1).getId();
        }
    }

}
