package com.ntg.clothes_online_project.service.Impl;

import com.ntg.clothes_online_project.dto.*;
import com.ntg.clothes_online_project.entity.User;
import com.ntg.clothes_online_project.jwt.JwtUtils;
import com.ntg.clothes_online_project.repository.UserRepository;
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
    private PasswordEncoder bcryptPasswordEncoder;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


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
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You're already registered please try to login!"));
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
        //List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        //	.collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(userDetails.getUserName(), userDetails.getUsername(), jwt));
    }

    public List<UserDTO> getAllUsers() {
       List<User>users= (List<User>) userRepository.findAll();
       List<UserDTO> usersDtos = null;
        if(!users.isEmpty()){
            usersDtos=new ArrayList<>();
            UserDTO userDTO = null;
            for (User user:users) {
                userDTO = new UserDTO();
                BeanUtils.copyProperties(user,userDTO);
                usersDtos.add(userDTO);
            }
        }

        return usersDtos;


    }

//    public ResponseEntity<?> updateProfile() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        CustomUser loginUser = (CustomUser) auth.getPrincipal();
//
//    }
 /*
 @GetMapping(value = "/getFullName")
	public String getAuthUserFullName() {
		String fullName = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser loginUser = (CustomUser) auth.getPrincipal();

		fullName = loginUser.getFirstName() + " " + loginUser.getLastName();

		return fullName;
	}
  */

}
