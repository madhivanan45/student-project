package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.student.servicer.Studentsev;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.student.model.Student;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Studentcontroller {
    @Autowired
    private Studentsev studentsev;
    @PostMapping("/newStudent")
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentsev.createStudent(student));
    }
    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentsev.getAllStudents());
    }
}
