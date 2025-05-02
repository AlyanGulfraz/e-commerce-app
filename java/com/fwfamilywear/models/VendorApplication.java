// File: app/src/main/java/com/fwfamilywear/models/VendorApplication.java
package com.fwfamilywear.models;
public class VendorApplication {
    private int id;
    private int userId;
    private String vendorName, phone, document, status;
    public VendorApplication(int id, int userId, String vendorName, String phone, String document, String status) {
        this.id = id;
        this.userId = userId;
        this.vendorName = vendorName;
        this.phone = phone;
        this.document = document;
        this.status = status;
    }
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getVendorName() { return vendorName; }
    public String getPhone() { return phone; }
    public String getDocument() { return document; }
    public String getStatus() { return status; }
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setVendorName(String name) { this.vendorName = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDocument(String doc) { this.document = doc; }
    public void setStatus(String status) { this.status = status; }
}
