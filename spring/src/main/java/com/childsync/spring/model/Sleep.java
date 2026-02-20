package com.childsync.spring.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
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
    @Column(name = "sleep_id")
    private Integer sleepId;

    @Column(name = "sleep_start")
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp sleepStart;

    @Column(name = "sleep_end")
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Timestamp sleepEnd;

    @Column(name = "user_id")
    private Integer userId;

    protected Sleep() { }

    public Sleep(Timestamp start, Timestamp end, Integer userId) {

        sleepStart = start;
        sleepEnd = end;
        this.userId = userId;

    }

    public Integer getId() {

        return sleepId;

    }

    public Timestamp getStart() {

        return sleepStart;

    }

    public Timestamp getEnd() {

        return sleepEnd;

    }

    public Integer getUserId() {

        return userId;

    }

    public void setStart(Timestamp start) {

        sleepStart = start;

    }

    public void setEnd(Timestamp end) {

        sleepEnd = end;

    }

    public void setUserId(Integer userId) {

        this.userId = userId;

    }

}
