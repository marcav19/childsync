package com.childsync.spring.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointment_id;

    private String appointment_reason;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp appointment_datetime;

    private String appointment_result;

    private Integer user_id;

    protected Appointment() { }

    public Appointment(String reason, Timestamp dateTime, String result, Integer userId) {

        appointment_reason = reason;
        appointment_datetime = dateTime;
        appointment_result = result;
        user_id = userId;

    }

    public Integer getId() {

        return appointment_id;

    }

    public String getReason() {

        return appointment_reason;

    }

    public Timestamp getDateTime() {

        return appointment_datetime;

    }

    public String getResult() {

        return appointment_result;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setReason(String reason) {

        appointment_reason = reason;

    }

    public void setDateTime(Timestamp dateTime) {

        appointment_datetime = dateTime;

    }

    public void setResult(String result) {

        appointment_result = result;
    
    }
    
    public void setUserId(Integer userId) {

        user_id = userId;

    }
    
}
