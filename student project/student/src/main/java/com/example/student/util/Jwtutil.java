package com.example.student.util;

import java.util.Date;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;



import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
@Component
public class Jwtutil {
    
    private String secrekey = "MadhiMadhi";
    private Long expiration = 3600000L; 
    
    // Use StandardCharsets to ensure consistency between signing and parsing
    private final SecretKey key = Keys.hmacShaKeyFor(secrekey.getBytes(java.nio.charset.StandardCharsets.UTF_8));

    public String generateToken(String email){
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact();
    }
    
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validatetoken(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}