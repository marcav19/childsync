package com.childsync.spring.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "baths")
public class Bath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bath_id;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp bath_datetime;

    private Integer user_id;

    protected Bath() { }

    public Bath(Timestamp dateTime, Integer userId) {

        bath_datetime = dateTime;
        user_id = userId;

    }

    public Integer getId() {

        return bath_id;

    }

    public Timestamp getDateTime() {

        return bath_datetime;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setDateTime(Timestamp dateTime) {

        bath_datetime = dateTime;

    }

    public void setUserId(Integer userId) {

        user_id = userId;

    }
    
}
