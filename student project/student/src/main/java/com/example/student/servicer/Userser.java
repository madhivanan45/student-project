package com.example.student.servicer;

import com.example.student.model.User;
import com.example.student.repository.Userres;
import org.springframework.stereotype.Service;

@Service
public class Userser { 
    private final Userres userres;

    
    public Userser(Userres userres) {
        this.userres = userres;
    }

    public User createuser(User user) {
        return userres.save(user);
    }

    public User getuser(Long id) {
        return userres.findById(id).orElse(null);
    }
}