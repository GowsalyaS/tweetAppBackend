package com.tweetapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tweetapp.repo.UserRepo;
import com.tweetapp.security.UserClassService;
import com.tweetapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authentication;

    @Autowired
    UserClassService userService;

    /**
     *
     * @param userClass
     * @return
     */

    @PostMapping("/login")
    public ResponseEntity<String> authenticateToken(@RequestBody JsonNode userClass) {
        System.out.println(userClass.get("login_id").asText());
         authentication.authenticate(new UsernamePasswordAuthenticationToken(userClass.get("login_id").asText(), userClass.get("password").asText()));
        UserDetails user=userService.loadUserByUsername(userClass.get("login_id").asText());
        String token = jwtUtil.generateToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}


