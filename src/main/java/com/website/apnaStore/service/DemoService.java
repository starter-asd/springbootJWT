package com.website.apnaStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.apnaStore.entity.User;
import com.website.apnaStore.repository.UserRepository;

@Service
public class DemoService {
    
    @Autowired
    UserRepository userRepository;

    
    public List<User> getAllUsers() throws Exception{
        List<User> allUser = userRepository.findAll();
       return allUser; 
    }
}
