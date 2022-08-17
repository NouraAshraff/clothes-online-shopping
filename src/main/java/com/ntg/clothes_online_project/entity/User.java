package com.ntg.clothes_online_project.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity(name = "users")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",  nullable = false)
    private String name;


    @Column(name = "userName",  nullable = false, unique = true)
    private String userName;


    @Column(name = "email",  nullable = false, unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;


    @Column(name = "gender")
    private String gender;

    @Column(name = "phoneNum")
    private String phoneNum;
    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "isActive")
    private Boolean isActive;


    public User(Long id, String name, String userName, String email, String password, String gender, String phoneNum, String city, String address) {
    }
}

