package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> { }