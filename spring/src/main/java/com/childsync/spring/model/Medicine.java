package com.childsync.spring.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicine_id;

    private String medicine_name;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp medicine_datetime;

    private String medicine_dosage;

    private Integer user_id;

    protected Medicine() { }

    public Medicine(String name, Timestamp dateTime, String dosage, Integer userId) {

        medicine_name = name;
        medicine_datetime = dateTime;
        medicine_dosage = dosage;
        user_id = userId;


    }

    public Integer getId() {

        return medicine_id;

    }

    public String getName() {

        return medicine_name;

    }

    public Timestamp getDateTime() {

        return medicine_datetime;

    }

    public String getDosage() {

        return medicine_dosage;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setName(String name) {

        medicine_name = name;

    }

    public void setDateTime(Timestamp dateTime) {

        medicine_datetime = dateTime;

    }

    public void setDosage(String dosage) {

        medicine_dosage = dosage;

    }

    public void setUserId(Integer userId) {

        user_id = userId;

    }
    
}
