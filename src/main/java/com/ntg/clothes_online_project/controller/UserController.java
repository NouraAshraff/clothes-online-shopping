package com.ntg.clothes_online_project.controller;

import com.ntg.clothes_online_project.dto.*;
import com.ntg.clothes_online_project.entity.Product;
import com.ntg.clothes_online_project.entity.User;
import com.ntg.clothes_online_project.jwt.JwtUtils;
import com.ntg.clothes_online_project.repository.UserRepository;
import com.ntg.clothes_online_project.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
   private UserService userService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


    @GetMapping(value = "/test")
    public String creatUser(){
        return"Hello from here";

    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You're already registered please try to login!"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
        //List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        //	.collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(userDetails.getUserName(), userDetails.getUsername(), jwt));
    }



}
