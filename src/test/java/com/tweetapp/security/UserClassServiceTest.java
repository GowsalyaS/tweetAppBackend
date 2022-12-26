package com.tweetapp.security;

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

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
 class UserClassServiceTest {

    @InjectMocks
    private UserClassService userClassService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        UserProfile userObj = new UserProfile();
        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(userObj));
        Assertions.assertEquals(userObj.getLoginid(),userClassService.loadUserByUsername("RM").getUsername());
    }

    @Test
    void loadUserByUsernameNotFoundExceptionTest(){
        UserProfile userObj = new UserProfile();

        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Assertions.assertThrows(UserNotFoundException.class,()->userClassService.loadUserByUsername("RM"));
    }
}
