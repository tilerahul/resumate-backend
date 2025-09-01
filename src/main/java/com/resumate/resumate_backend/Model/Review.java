package com.resumate.resumate_backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "reviews")
public class Review {

    @Id
    private String id;

    private String userId;   // Reference to User document
    private String imgUrl;
    private String username;
    private int star;
    private String comment;
    private Instant createdAt;

    public Review() {
        this.createdAt = Instant.now();
    }

    public Review(String userId, String imgUrl, String username, int star, String comment) {
        this.userId = userId;
        this.imgUrl = imgUrl;
        this.username = username;
        this.star = star;
        this.comment = comment;
        this.createdAt = Instant.now();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getStar() { return star; }
    public void setStar(int star) { this.star = star; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
