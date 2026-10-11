package com.example.student.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.Student;

public interface Studentres extends JpaRepository<Student, Long> {
    
}
