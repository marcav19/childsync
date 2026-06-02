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
@Table(name = "sleeps")
public class Sleep {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private LocalDateTime start;

    @Column(unique = true)
    @NotNull
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    protected Sleep() { }

    public Sleep(LocalDateTime start, LocalDateTime end, User user) {

        this.start = start;
        this.end = end;
        this.user = user;

    }

    public Integer getId() {

        return id;

    }

    public LocalDateTime getStart() {

        return start;

    }

    public LocalDateTime getEnd() {

        return end;

    }

    public User getUser() {

        return user;

    }

    public void setStart(LocalDateTime start) {

        this.start = start;

    }

    public void setEnd(LocalDateTime end) {

        this.end = end;

    }

    public void setUser(User user) {

        this.user = user;

    }

}
