package com.childsync.spring.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meal_id;

    private String meal_name;

    @JsonFormat (pattern = "MM/dd/yyyy HH:mm")
    private Timestamp meal_datetime;

    private String meal_comment;

    private Integer user_id;

    protected Meal() { }

    public Meal(String name, Timestamp dateTime, String comment, Integer userId) {

        meal_name = name;
        meal_datetime = dateTime;
        meal_comment = comment;
        user_id = userId;
        
    }

    public Meal(String name, Timestamp dateTime, Integer userId) {

        meal_name = name;
        meal_datetime = dateTime;
        user_id = userId;
        
    }

    public Integer getId() {

        return meal_id;

    }

    public String getName() {

        return meal_name;

    }

    public Timestamp getDateTime() {

        return meal_datetime;

    }

    public String getComment() {

        return meal_comment;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setName(String name) {

        meal_name = name;

    }

    public void setDateTime(Timestamp dateTime) {

        meal_datetime = dateTime;

    }

    public void setComment(String comment) {

        meal_comment = comment;

    }

    public void setUserId(Integer userId) {

        user_id = userId;

    }
    
}
