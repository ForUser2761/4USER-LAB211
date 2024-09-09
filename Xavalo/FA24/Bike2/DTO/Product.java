package DTO;

import java.util.List;

import utils.IdGenerator;

public class Product {
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private List<Double> listPrice;

    public Product() {
        this.id = IdGenerator.generateId("P");
    }

    // Constructor
    public Product(String name, String brandId, String categoryId, int modelYear, List<Double> listPrice) {
        this.id = IdGenerator.generateId("P");
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public Product(String id , String name, String brandId, String categoryId, int modelYear, List<Double> listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public List<Double> getListPrice() {
        return listPrice;
    }

    public void setListPrice(List<Double> listPrice) {
        this.listPrice = listPrice;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-15s %-15s %-15s %-15s ", id, name, brandId, categoryId, modelYear));
        sb.append("List Price: [");
        for (int i = 0; i < listPrice.size(); i++) {
            sb.append(listPrice.get(i));
            if (i < listPrice.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}