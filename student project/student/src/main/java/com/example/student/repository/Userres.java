package com.example.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.User;

public interface Userres extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}