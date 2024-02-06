package com.website.apnaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.apnaStore.entity.User;
import com.website.apnaStore.service.AuthenticationService;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request){
        return ResponseEntity.ok(authService.authenticate(request)); 
    }
}
