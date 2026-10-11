package com.example.student.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.student.model.User;
import com.example.student.repository.Userres;
import com.example.student.servicer.Userser;
import com.example.student.util.Jwtutil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class Usercontroller {

    private final Userres userres;
    private final Userser userser;
    private final Jwtutil jwtutil;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection (BEST PRACTICE)
    public Usercontroller(Userres userres, Userser userser, Jwtutil jwtutil, PasswordEncoder passwordEncoder) {
        this.userres = userres;
        this.userser = userser;
        this.jwtutil = jwtutil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        // Validation
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        if (userres.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        userser.createuser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginuser(@RequestBody Map<String,String> body) {
        String email=body.get("email");
        String password= body.get("password");
        if(email==null || password==null){
            return ResponseEntity.badRequest().body("Enter email and Password");
        }
        User user=userres.findByEmail(email).orElse(null);
        if(user==null || !passwordEncoder.matches(password, user.getPassword())){
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        String token=jwtutil.generateToken(email);
        return ResponseEntity.ok().body(Map.of("token", token));
    }
    }