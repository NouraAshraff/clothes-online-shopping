package com.ntg.clothes_online_project.controller;

import com.ntg.clothes_online_project.dto.UserDTO;
import com.ntg.clothes_online_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class UserController {
    @Autowired
    UserService userService;

    public ResponseEntity<?> creatUser(UserDTO userdto){
        return userService.creatUser(userdto);

    }
}
