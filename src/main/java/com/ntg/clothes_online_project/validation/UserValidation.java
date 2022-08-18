package com.ntg.clothes_online_project.validation;

import com.ntg.clothes_online_project.dto.MessageResponse;
import com.ntg.clothes_online_project.entity.User;
import com.ntg.clothes_online_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<MessageResponse> validateUser(User user) {
        if (user.getEmail()==null || user.getEmail().length()==0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email can not be null!"));
        }
        if (user.getName()==null || user.getName().length()==0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Name can not be null!"));
        }
        if (user.getUserName()==null || user.getUserName().length()==0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User Name can not be null!"));
        }
        if (user.getPassword()==null || user.getPassword().length()==0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: password can not be null!"));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You're already registered please try to login!"));
        }
        if (userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You're already registered please try to login!"));
        }
        return null;

    }
}
