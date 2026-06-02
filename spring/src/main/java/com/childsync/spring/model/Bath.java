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
@Table(name = "baths")
public class Bath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "datetime", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    protected Bath() { }

    public Bath(LocalDateTime dateTime, User user) {

        this.dateTime = dateTime;
        this.user = user;

    }

    public Integer getId() {

        return id;

    }

    public LocalDateTime getDateTime() {

        return dateTime;

    }

    public User getUser() {

        return user;

    }

    public void setDateTime(LocalDateTime dateTime) {

        this.dateTime = dateTime;

    }

    public void setUser(User user) {

        this.user = user;

    }
    
}
