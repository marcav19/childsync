package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.childsync.spring.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> { }