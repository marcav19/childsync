package com.childsync.spring.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sleep")
public class Sleep {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sleep_id;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp sleep_start;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp sleep_end;

    private Integer user_id;

    protected Sleep() { }

    public Sleep(Timestamp start, Timestamp end, Integer userId) {

        sleep_start = start;
        sleep_end = end;
        user_id = userId;

    }

    public Integer getId() {

        return sleep_id;

    }

    public Timestamp getStart() {

        return sleep_start;

    }

    public Timestamp getEnd() {

        return sleep_end;

    }

    public Integer getUserId() {

        return user_id;

    }

    public void setStart(Timestamp start) {

        sleep_start = start;

    }

    public void setEnd(Timestamp end) {

        sleep_end = end;

    }

    public void setUserId(Integer userId) {

        user_id = userId;

    }

}
