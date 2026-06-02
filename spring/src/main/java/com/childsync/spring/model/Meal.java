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
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "datetime", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String name;

    @NotNull
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    protected Meal() { }

    public Meal(String name, LocalDateTime dateTime, String comment, User user) {

        this.name = name;
        this.dateTime = dateTime;
        this.comment = comment;
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
    public String getComment() {

        return comment;

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

    public void setComment(String comment) {

        this.comment = comment;

    }

    public void setUser(User user) {

        this.user = user;

    }
    
}
