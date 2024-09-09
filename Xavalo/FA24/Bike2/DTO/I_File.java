package DTO;

public interface I_File {
    public static final String BRAND_FILE_NAME = "brands.txt";
    public static final String PRODUCT_FILE_NAME = "products.txt";
    public static final String CATEGORY_FILE_NAME = "categories.txt";
    
    void readFromFile(String fileName);

    void writeToFile(String fileName);
    
} 
