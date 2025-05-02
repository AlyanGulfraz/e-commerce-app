// File: app/src/main/java/com/fwfamilywear/models/CartItem.java
package com.fwfamilywear.models;
public class CartItem {
    private int id;
    private int userId, productId;
    private int quantity;
    private String productName;
    private double productPrice;
    public CartItem(int id, int userId, int productId, int quantity, String productName, double productPrice) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public String getProductName() { return productName; }
    public double getProductPrice() { return productPrice; }
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setProductName(String name) { this.productName = name; }
    public void setProductPrice(double price) { this.productPrice = price; }
}
