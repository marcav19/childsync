package com.childsync.spring.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "datetime", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    protected Activity() { }

    public Activity(LocalDateTime dateTime, String name, User user) {

        this.dateTime = dateTime;
        this.name = name;
        this.user = user;

    }

    public Integer getId() {

        return id;

    }
    
    public LocalDateTime getDateTime() {

        return dateTime;

    }

    public String getName() {

        return name;

    }

    public User getUser() {

        return user;

    }
    
    public void setDateTime(LocalDateTime dateTime) {
    
        this.dateTime = dateTime;
    
    }
    
    public void setName(String name) {

        this.name = name;

    }

    public void setUser(User user) {

        this.user = user;

    }
    
}
