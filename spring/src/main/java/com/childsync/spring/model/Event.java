package com.childsync.spring.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    private String event_name;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp event_datetime;

    private Integer user_id;

    protected Event() { }

    public Event(String name, Timestamp dateTime, Integer userId) {

        event_name = name;
        event_datetime = dateTime;
        user_id = userId;

    }

    public Integer getId() {

        return event_id;

    }

    public String getName() {

        return event_name;

    }

    public Timestamp getDateTime() {

        return event_datetime;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setName(String name) {

        event_name = name;

    }

    public void setDateTime(Timestamp dateTime) {

        event_datetime = dateTime;

    }

    public void setUserId(Integer userId) {

        user_id = userId;

    }
    
}
