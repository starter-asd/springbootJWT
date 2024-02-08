package com.website.apnaStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.apnaStore.entity.User;
import com.website.apnaStore.service.DemoService;

@RestController
public class DemoController {
    
    @Autowired
    private DemoService demoService;

    @GetMapping("/hello")
    public List<User> getAllUsers() throws Exception{
        return demoService.getAllUsers();
    }
}

