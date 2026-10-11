package com.example.student.servicer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Student;
import com.example.student.repository.Studentres;
@Service
public class Studentsev {
    
    private final Studentres studentres;

    @Autowired
    public Studentsev(Studentres studentres) {
        this.studentres = studentres;
    }

    public Student createStudent(Student student) {
        return studentres.save(student);
    }
    public List<Student> getAllStudents(){
        return studentres.findAll();
    }
}
