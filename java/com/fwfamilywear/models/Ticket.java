// File: app/src/main/java/com/fwfamilywear/models/Ticket.java
package com.fwfamilywear.models;
public class Ticket {
    private int id;
    private int userId;
    private String subject, description, status;
    public Ticket(int id, int userId, String subject, String description, String status) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.description = description;
        this.status = status;
    }
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getSubject() { return subject; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
}
