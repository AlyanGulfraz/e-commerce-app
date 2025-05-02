// File: app/src/main/java/com/fwfamilywear/models/Order.java
package com.fwfamilywear.models;
public class Order {
    private int id;
    private int userId, productId, quantity;
    private String status;
    private String productName;
    public Order(int id, int userId, int productId, int quantity, String status, String productName) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.productName = productName;
    }
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public String getProductName() { return productName; }
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setStatus(String status) { this.status = status; }
    public void setProductName(String name) { this.productName = name; }
}
