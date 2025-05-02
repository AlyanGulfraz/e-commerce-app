// File: app/src/main/java/com/fwfamilywear/models/SaleRequest.java
package com.fwfamilywear.models;
public class SaleRequest {
    private int id;
    private int userId, productId;
    private String status;
    private String productName;
    public SaleRequest(int id, int userId, int productId, String status, String productName) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.status = status;
        this.productName = productName;
    }
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public String getStatus() { return status; }
    public String getProductName() { return productName; }
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setStatus(String status) { this.status = status; }
    public void setProductName(String name) { this.productName = name; }
}
