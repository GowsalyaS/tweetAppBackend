package com.tweetapp.service;

import com.tweetapp.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TweetappUserService {

  String registerUser(UserProfile users);



  String toresetPassword(String userName,UserProfile users);

  List<UserProfile> getAllUsers();

  List<UserProfile> userNameSearch(UserProfile user);

}
