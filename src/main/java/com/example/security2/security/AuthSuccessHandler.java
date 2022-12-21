package com.example.security2.security;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.security2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
 //   private final int expTime;
  //  private final String secret;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UserService userService;

    public AuthSuccessHandler( UserService userService) {
       // this.expTime = expTime;
     //  this.secret = secret;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = JWT.create()
                .withSubject(userService.getUserByUsername(principal.getUsername()).getEmail())
                .withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli() + 3600000))
                .sign(Algorithm.HMAC256("secret"));
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type", "Application/json");
        response.getWriter().write("{\"token\": \""+token+"\"}");
    }

    


}
