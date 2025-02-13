package com.springsecurity.service;

import com.springsecurity.dao.UserRepo;
import com.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser (User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }
}
