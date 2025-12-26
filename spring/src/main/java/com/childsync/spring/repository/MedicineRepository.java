package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> { }
