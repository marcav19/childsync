package com.childsync.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.childsync.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> { }
