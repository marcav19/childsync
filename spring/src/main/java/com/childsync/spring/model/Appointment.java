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
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "datetime", unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String reason;

    @NotNull
    private String result;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    protected Appointment() { }

    public Appointment(LocalDateTime dateTime, String reason, String result, User user) {

        this.dateTime = dateTime;
        this.reason = reason;
        this.result = result;
        this.user = user;

    }

    public Integer getId() {

        return id;

    }
    
    public LocalDateTime getDateTime() {

        return dateTime;

    }

    public String getReason() {

        return reason;

    }

    public String getResult() {

        return result;

    }

    public User getUser() {

        return user;

    }

    public void setDateTime(LocalDateTime dateTime) {

        this.dateTime = dateTime;

    }

    public void setReason(String reason) {

        this.reason = reason;

    }

    public void setResult(String result) {

        this.result = result;
    
    }
    
    public void setUser(User user) {

        this.user = user;

    }
    
}
