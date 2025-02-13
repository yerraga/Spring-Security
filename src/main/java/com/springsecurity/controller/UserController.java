package com.springsecurity.controller;

import com.springsecurity.model.User;
import com.springsecurity.service.JWTService;
import com.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @PostMapping ("/registerUser")
    public User saveUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }
    @GetMapping("/csrf-token")
    public CsrfToken getCSRFToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping ("/login")
    public String userLogin(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated() ){
            return jwtService.generateToken(user.getUsername());
        }
        else {
            return "Login Failed";
        }
    }
}
