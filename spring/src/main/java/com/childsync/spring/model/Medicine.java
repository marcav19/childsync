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
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "datetime", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String name;

    @NotNull
    private String dosage;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    protected Medicine() { }

    public Medicine(LocalDateTime dateTime, String name, String dosage, User user) {

        this.dateTime = dateTime;
        this.name = name;
        this.dosage = dosage;
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

    public String getDosage() {

        return dosage;

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

    public void setDosage(String dosage) {

        this.dosage = dosage;

    }

    public void setUser(User user) {

        this.user = user;

    }
    
}
