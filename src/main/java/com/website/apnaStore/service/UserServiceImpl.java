package com.website.apnaStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.website.apnaStore.repository.UserRepository;


@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository
                        .findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not found" + username));
    }
    
}
