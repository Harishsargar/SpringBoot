package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.User;

public interface UserRepo extends JpaRepository<User,String> {

}
