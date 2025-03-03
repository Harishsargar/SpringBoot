package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.entity.ROLE;
import com.example.crud.entity.User;
import com.example.crud.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    // create a new user
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createuser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // fetching the user by id
    @GetMapping("/fetchById")
    public ResponseEntity<?> fetchById(@RequestParam String id){
        User user = userService.fetchById(id);
        if(user==null){
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }

    // fetch all users
    @GetMapping("/fetchAllUser")
    public ResponseEntity<List<User>> fetchAllUser(){
        return new ResponseEntity<>(userService.fetchAllUser() , HttpStatus.OK);
    }
    

    // fetch user having role has "ADMIN"
    @GetMapping("/fetchAllAdmin")
    public ResponseEntity<List<User>> fetchAllAdmin(){
        return new ResponseEntity<>(userService.fetchAllAdmin(),HttpStatus.OK);
    }

    // fetch users that have register in last 7 days
    @GetMapping("/sevenDaysAgo")
    public ResponseEntity<List<User>> fetchUsersRegisterSevenDaysAgo(){
        return new ResponseEntity<>(userService.fetchUserRegister7DaysAgo() , HttpStatus.OK);
    }

    // udating the user in db
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userId){
        User newUser = userService.updateUser(user, userId);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    // update the roles in batch
    @PutMapping("/updateRoles")
    public ResponseEntity<?> updateRoles(@RequestParam ROLE oldRole, @RequestParam ROLE newRole){
        int totalRowUpated = userService.updateRole(oldRole, newRole);
        return new ResponseEntity<>("number of total rows updated: "+totalRowUpated , HttpStatus.OK);
    }

}
