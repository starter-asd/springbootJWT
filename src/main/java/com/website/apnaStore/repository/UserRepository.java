package com.website.apnaStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.website.apnaStore.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByUsername(String username);
}
