package com.resumate.resumate_backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "contacts")
public class Contact {

    @Id
    private String id;

    @DBRef
    private User user;

    private String name;
    private String email;
    private String message;
    private Date createdAt = new Date();

    // Constructors
    public Contact() {}

    public Contact(User user, String name, String email, String message) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.message = message;
        this.createdAt = new Date();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
