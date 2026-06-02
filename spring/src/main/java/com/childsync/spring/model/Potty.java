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
@Table(name = "potties")
public class Potty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "datetime", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;
    
    protected Potty() { }

    public Potty(LocalDateTime dateTime, String description, User user) {

        this.dateTime = dateTime;
        this.description = description;
        this.user = user;

    }

    public Integer getId() {

        return id;

    }

    public LocalDateTime getDateTime() {

        return dateTime;

    }

    public String getDescription() {

        return description;

    }

    public User getUser() {

        return user;

    }

    public void setDateTime(LocalDateTime dateTime) {

        this.dateTime = dateTime;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public void setUser(User user) {

        this.user = user;

    }
    
}
