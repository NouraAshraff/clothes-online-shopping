package com.ntg.clothes_online_project.user.service;

import com.ntg.clothes_online_project.user.dto.*;
import com.ntg.clothes_online_project.user.entity.User;
import com.ntg.clothes_online_project.user.jwt.JwtUtils;
import com.ntg.clothes_online_project.user.repository.UserRepository;
import com.ntg.clothes_online_project.user.validation.UserValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
   private UserValidation userValidation;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("Email " + email + " Not found");

        return new CustomUser(user.getEmail(),
                user.getPassword(),true, true, true,true,
               new ArrayList<>(), user.getUserName());

    }


    public ResponseEntity<?> registerUser(User user) {
        ResponseEntity<MessageResponse> validation = userValidation.validateUser(user);
        if(validation!=null){
            return validation;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUser userDetails = (CustomUser) authentication.getPrincipal();

        return ResponseEntity.ok(new LoginResponse(userDetails.getUserName(), userDetails.getUsername(), jwt));
    }

    public List<UserDTO> getAllUsers() {
       List<User>users= (List<User>) userRepository.findAll();
       List<UserDTO> usersDTOS = null;
        if(!users.isEmpty()){
            usersDTOS=new ArrayList<>();
            UserDTO userDTO ;

            for (User user:users) {
                userDTO = new UserDTO();
                BeanUtils.copyProperties(user,userDTO);
                usersDTOS.add(userDTO);
            }
        }
        return usersDTOS;
    }

    public ResponseEntity<?> updateProfile(UpdateProfileRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user!= null){
            user.setName(request.getName());
            user.setPassword(encoder.encode(request.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Updated successfully!"));

        }
        return ResponseEntity.badRequest().body(new MessageResponse("No User was found with this mail"));

    }
}
