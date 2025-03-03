package com.springsecurity.service;

import com.springsecurity.dao.UserRepo;
import com.springsecurity.enums.Role;
import com.springsecurity.exception.UserNameAlreadyExistException;
import com.springsecurity.exception.UserNameAndPasswordNotMatch;
import com.springsecurity.exception.UserNotFoundException;
import com.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser (User user){
        Optional<User> userDetails = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));
        if (userDetails.isPresent()){
            throw new UserNameAlreadyExistException("User name already exist, please use another username");
        }
        else{
            user.setRole(Role.USER);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return user;
        }
    }
    public String login(User user) {

        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password must be provided");
        }

        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            return jwtService.generateToken(user.getUsername());

        } catch (Exception e) {

            throw new UserNameAndPasswordNotMatch("Username and password do not match");
        }
    }

}
