package com.tweetapp.security;

import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.Constants;
import com.tweetapp.model.UserProfile;
import com.tweetapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserClassService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserProfile user= userRepo.findById(loginId).get();
        if(user.getLoginid()==null){
            throw new  UserNotFoundException(Constants.USERNOTFOUND);
        }
        UserClass userLogin=new UserClass(user);
        return new User(userLogin.getUsername(),userLogin.getPassword(),new ArrayList<>());
    }
}
