package com.example.crud.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.entity.User;
import com.example.crud.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createuser(User user){
        String randomId = UUID.randomUUID().toString();
        user.setId(randomId);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepo.save(user);
    }

    public User fetchById(String id){
        return userRepo.findById(id).orElse(null);
    }

}
