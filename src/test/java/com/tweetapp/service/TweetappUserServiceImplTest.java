package com.tweetapp.service;

import com.tweetapp.config.SecurityConfig;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserProfile;
import com.tweetapp.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
 class TweetappUserServiceImplTest {
    @InjectMocks
    private TweetappUserServiceImpl userImpl;

    @Mock
    private UserRepo userRepo1;

    @Mock
    private SecurityConfig config;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerUserTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        Mockito.when(config.passwordEncoder())
                .thenReturn(passwordEncoder());
        userObj.setContactnumber(1234567890l);
        userRepo1.save(userObj);
        String str=userImpl.registerUser(userObj);
        Assertions.assertEquals(" Successfully Created",str);

    }



    @Test
     void toresetPasswordTestError(){
        UserProfile userObj = new UserProfile();

        Mockito.when(userRepo1.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->userImpl.toresetPassword("Jin",userObj));
    }

    @Test
     void toGetAllUserTest(){
        List<UserProfile> listUser=new ArrayList<>();
        UserProfile userObj = new UserProfile();
        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);

        listUser.add(userObj);
        Mockito.when(userRepo1.findAll()).thenReturn(listUser);
        Assertions.assertEquals(1,userImpl.getAllUsers().size());
    }


    @Test
    void userNameSearchTest(){
        UserProfile userObj = new UserProfile();
        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Mockito.when(userRepo1.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(userObj));
        List<UserProfile> users=userImpl.userNameSearch(userObj);
        Assertions.assertEquals(1,users.size());
    }

    @Test
     void userNameSearchNotFoundTest(){
        List<UserProfile> userList= null;
        UserProfile userObj = new UserProfile();
        userObj.setLoginid("RM");
        Mockito.when(userRepo1.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->userImpl.userNameSearch(userObj));
    }


}
