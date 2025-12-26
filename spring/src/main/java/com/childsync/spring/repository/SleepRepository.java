package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.Sleep;

public interface SleepRepository extends JpaRepository<Sleep, Integer> { }
