// File: app/src/main/java/com/fwfamilywear/models/Product.java
package com.fwfamilywear.models;
public class Product {
    private int id;
    private String name;
    private int categoryId;
    private double price;
    private String description, image;
    public Product(int id, String name, int categoryId, double price, String description, String image) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getCategoryId() { return categoryId; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setImage(String image) { this.image = image; }
}
