// File: app/src/main/java/com/fwfamilywear/models/Category.java
package com.fwfamilywear.models;
public class Category {
    private int id;
    private String name, image;
    public Category(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getImage() { return image; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setImage(String image) { this.image = image; }
}
