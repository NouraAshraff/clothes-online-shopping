package com.ntg.clothes_online_project.user.controller;
import com.ntg.clothes_online_project.user.dto.LoginRequest;
import com.ntg.clothes_online_project.user.dto.UpdateProfileRequest;
import com.ntg.clothes_online_project.user.dto.UserDTO;
import com.ntg.clothes_online_project.user.entity.User;
import com.ntg.clothes_online_project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
   private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.registerUser(user);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @PutMapping(value = "/profile")
    public  ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request){
        return userService.updateProfile(request);
    }

    @GetMapping(value = "/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }







}
