package com.resumate.resumate_backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "otp")
public class OTP {

    @Id
    private String id;

    private String email;
    private String otp;

    @Indexed(expireAfter = "300")
    private Instant createdAt;

    public OTP() {
        this.createdAt = Instant.now();
    }

    public OTP(String email, String otp) {
        this.email = email;
        this.otp = otp;
        this.createdAt = Instant.now();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

