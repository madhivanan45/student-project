package com.example.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="madhi")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    // Standard No-Args Constructor
    public User() {}

    // Standard All-Args Constructor
    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}