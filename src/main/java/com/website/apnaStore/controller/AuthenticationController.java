package com.website.apnaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.website.apnaStore.entity.User;
import com.website.apnaStore.service.AuthenticationService;

@RestController
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authService;


    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request){
        return ResponseEntity.ok(authService.register(request));
    }

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request){
        if (request.getRole() == null) {
            request.setRole("USER");
        }
        return ResponseEntity.ok(authService.authenticate(request)); 
    }
}
