package com.example.springsecuritydemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
