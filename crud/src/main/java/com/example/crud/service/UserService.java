package com.example.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.entity.ROLE;
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

    public List<User> fetchAllUser(){
        return userRepo.findAll();
    }

    public List<User> fetchAllAdmin(){
        return userRepo.findByRole(ROLE.ADMIN);
    }

    public List<User> fetchUserRegister7DaysAgo(){
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return userRepo.findUserRegisterInlast7Days(sevenDaysAgo);
    }

    public User updateUser(User user, String id){
    
        User oldUser = userRepo.findById(id).orElse(null);
        if (oldUser == null) {
            return null;
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setUpdatedAt(LocalDateTime.now());
        return userRepo.save(oldUser);
    }


    public int updateRole(ROLE oldRole, ROLE newRole){
        return userRepo.updateRollForAll(oldRole, newRole);
    }

}
