package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.Bath;

public interface BathRepository extends JpaRepository<Bath, Integer> { }