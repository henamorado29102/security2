package com.example.security2.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1")
public class HomeController {
    
    @GetMapping("/user")
    public ResponseEntity<String> userEndPoint(){
        return new ResponseEntity<>("Hello user!", HttpStatusCode.valueOf(200));
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminEndPoint(){
        return new ResponseEntity<>("Hello admin!", HttpStatusCode.valueOf(200));
    }
}
