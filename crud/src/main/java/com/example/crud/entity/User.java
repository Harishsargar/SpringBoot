package com.example.crud.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
