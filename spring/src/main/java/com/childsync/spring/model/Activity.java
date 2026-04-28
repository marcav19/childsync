package com.childsync.spring.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    private LocalDateTime datetime;

    @NotNull
    private String name;

    @NotNull
    private Integer userid;

    protected Activity() { }

    public Activity(LocalDateTime datetime, String name, Integer userid) {

        this.datetime = datetime;
        this.name = name;
        this.userid = userid;

    }

    public Integer getId() {

        return id;

    }
    
    public LocalDateTime getDatetime() {

        return datetime;

    }

    public String getName() {

        return name;

    }

    public Integer getUserid() {

        return userid;

    }
    
    public void setDatetime(LocalDateTime datetime) {
    
        this.datetime = datetime;
    
    }
    
    public void setName(String name) {

        this.name = name;

    }

    public void setUserid(Integer userid) {

        this.userid = userid;

    }
    
}
