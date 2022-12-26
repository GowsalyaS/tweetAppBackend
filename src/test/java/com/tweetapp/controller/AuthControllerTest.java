package com.tweetapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.repo.UserRepo;
import com.tweetapp.security.UserClassService;
import com.tweetapp.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserRepo userRepo;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authentication;

    @Mock
    private UserClassService userService;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Authentication authentication1;

    @Autowired
    UserDetails user;

    /**
     *
     * @throws JsonProcessingException
     */
    @Test
    void authenticateTokenTest() throws JsonProcessingException {
        String str="{ \"login_id\" :\"WWH\",\"password\" :\"WWH\"} ";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        when(userService.loadUserByUsername(Mockito.anyString())).thenReturn( new User("WWH","WWH",new ArrayList<>()));
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("token");
        Assertions.assertEquals(200,authController.authenticateToken(jsonNode).getStatusCodeValue());
    }
}
