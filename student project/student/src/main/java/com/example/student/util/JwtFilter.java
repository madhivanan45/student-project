package com.example.student.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.Collections;

import com.example.student.util.Jwtutil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    private final Jwtutil jwtutil; // Adding final is good practice for constructor injection
    
    // Manual Constructor for dependency injection
    public JwtFilter(Jwtutil jwtutil) {
        this.jwtutil = jwtutil;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader = request.getHeader("Authorization");
        
        // Ensure there is a space after "Bearer" in your check
        if(authheader != null && authheader.startsWith("Bearer ")){
            String token = authheader.substring(7);
            try {
                String email = jwtutil.extractEmail(token);
                
                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    if(jwtutil.validatetoken(token)){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.emptyList());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                // Catches SignatureException or ExpiredJwtException to prevent app crash
                logger.error("Could not set user authentication", e);
            }
        }
        filterChain.doFilter(request, response);
    }
}