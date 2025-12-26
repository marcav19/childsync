package com.childsync.spring.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "potty")
public class Potty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer potty_id;

    private String potty_description;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp potty_datetime;

    private Integer user_id;
    
    protected Potty() { }

    public Potty(String description, Timestamp dateTime, Integer userId) {

        potty_description = description;
        potty_datetime = dateTime;
        user_id = userId;

    }

    public Integer getId() {

        return potty_id;

    }

    public String getDescription() {

        return potty_description;

    }

    public Timestamp getDateTime() {

        return potty_datetime;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setDescription(String description) {

        potty_description = description;

    }

    public void setDateTime(Timestamp dateTime) {

        potty_datetime = dateTime;

    }

    public void setUserId(Integer userId) {

        user_id = userId;

    }
    
}
