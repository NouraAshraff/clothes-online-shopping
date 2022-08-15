package com.ntg.clothes_online_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email",  nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;
}
