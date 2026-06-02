package com.childsync.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @Email
    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private String password;

    protected User() { }

    public User(String name, String email, String password) {
        
        this.name = name;
        this.email = email;
        this.password = password;
    
    }

    public Integer getId() {
    
        return id;
    
    }

    public String getName() {
    
        return name;
    
    }

    public String getEmail() {
    
        return email;
    
    }
    
    
    public String getPassword() {
    
        return password;
    
    }

    public void setName(String name) {

        this.name = name;

    }

    public void setEmail(String email) {

        this.email = email;

    }
    
    public void setPassword(String password) {

        this.password = password;

    }

}
