package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.Potty;

public interface PottyRepository extends JpaRepository<Potty, Integer> { }