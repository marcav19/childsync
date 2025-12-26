package com.childsync.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String user_name;
    private String user_email;

    protected User() { }

    public User(String name, String email) {
        
        user_name = name;
        user_email = email;
    
    }

    public Integer getId() {
    
        return user_id;
    
    }

    public String getName() {
    
        return user_name;
    
    }

    public String getEmail() {
    
        return user_email;
    
    }
    
    public void setName(String name) {

        user_name = name;

    }

    public void setEmail(String email) {

        user_email = email;

    }
    
}
