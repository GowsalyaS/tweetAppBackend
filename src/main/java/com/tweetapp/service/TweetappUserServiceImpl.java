package com.tweetapp.service;

import com.tweetapp.config.SecurityConfig;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.Constants;
import com.tweetapp.model.UserProfile;
import com.tweetapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TweetappUserServiceImpl implements TweetappUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    SecurityConfig config;

    @Override
    public String registerUser(UserProfile users) {
        users.setPassword( config.passwordEncoder().encode(users.getPassword()));
        userRepo.save(users);
        return " Successfully Created";
    }



    @Override
    public String toresetPassword(String userName,UserProfile users) {
        if(userRepo.findById(userName).isPresent()){
           UserProfile userdata= userRepo.findById(userName).get();
            userdata.setPassword( config.passwordEncoder().encode(users.getPassword()));
            userdata.setConfirmpassword(users.getPassword());
                userRepo.save(userdata);
                return "Password changed successfully";
            }

           throw new UserNotFoundException(Constants.USERNOTFOUND);


    }

    @Override
    public List<UserProfile> getAllUsers() {

//        List<UserProfile> actualList = new ArrayList<UserProfile>();
//        userRepo.findAll()..forEachRemaining(actualList::add);

        List<UserProfile> actualList = StreamSupport
                .stream(userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());


        return actualList;
    }

    @Override
    public List<UserProfile> userNameSearch(UserProfile user) {
        if(userRepo.findById(user.getLoginid())!=null){
           UserProfile userList= userRepo.findById(user.getLoginid()).get();
            List<UserProfile> userNamesList=new ArrayList<>();
           /* for(int i=0;i<userList.size();i++){
                userNamesList.add(userList.get(i).getLoginid());
            }*/
            userNamesList.add(userList);
            return userNamesList;
        }
        throw new UserNotFoundException(Constants.USERNOTFOUND);
    }
}
