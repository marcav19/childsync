package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> { }