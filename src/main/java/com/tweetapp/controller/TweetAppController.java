package com.tweetapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserProfile;
import com.tweetapp.service.TweetAppTweetsServiceImpl;
import com.tweetapp.service.TweetappUserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;


@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*")
public class TweetAppController {

    @Autowired
    private TweetappUserServiceImpl userServiceImpl;

    @Autowired
    private TweetAppTweetsServiceImpl tweetServiceImpl;

    Logger logger = LoggerFactory.getLogger(TweetAppController.class);

    /**
     *
     * @param users
     * @return String
     */
    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody UserProfile users){
        logger.info("User registered");
        return new ResponseEntity<>(userServiceImpl.registerUser(users),HttpStatus.OK);
    }

    /**
     *
     * @param userName
     * @param users
     * @return
     */

    @PostMapping("/{userName}/forgot")
    public ResponseEntity<String> resetPassword(@Valid @PathVariable String userName ,@RequestBody UserProfile users){
        logger.info("user trying to reset password");
        return new ResponseEntity<>(userServiceImpl.toresetPassword(userName,users),HttpStatus.ACCEPTED);
    }

    /**
     *
     * @return list of user
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getAllUser(){
        logger.info("get all users");
        return new ResponseEntity<>(userServiceImpl.getAllUsers(),HttpStatus.OK);
    }

    /**
     *
     * @param userName
     * @param tweets
     * @return
     */
    @PostMapping("/{userName}/add")
    public ResponseEntity<String> postComments(@Valid @PathVariable String userName,@Valid  @RequestBody Tweet tweets){
        logger.info("Post added");
        return new ResponseEntity<>(tweetServiceImpl.addPost(userName,tweets),HttpStatus.OK);
    }

    /**
     *
     * @return list of tweets
     */
    @GetMapping("/users/all")
    public ResponseEntity<List<Tweet>> getAllTweets(){
        logger.info("displaying all tweets");
        return new ResponseEntity<>(tweetServiceImpl.getTweets(),HttpStatus.OK);
    }

    /**
     *
     * @param userName
     * @param id
     * @param tweets
     * @return
     */
    @PutMapping("/{userName}/update/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable String userName,@PathVariable String id ,@Valid @RequestBody Tweet tweets){
        logger.info("user modified Tweet");
        return new ResponseEntity<>(tweetServiceImpl.modifyTweet(userName,id,tweets),HttpStatus.OK);
    }

    /**
     *
     * @param tweet
     * @return
     */

    @PostMapping("/username")
    public ResponseEntity<List<Tweet>> getAllTweet(@RequestBody Tweet tweet){
        logger.info("Getting Users all tweets");
        return new ResponseEntity<>(tweetServiceImpl.getTweetByName(tweet),HttpStatus.OK);
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/user/search/username")
    public ResponseEntity<List<UserProfile>> searchByUserName(@RequestBody UserProfile user){
        logger.info("Find users");
        return new ResponseEntity<>(userServiceImpl.userNameSearch(user),HttpStatus.OK);
    }

    /**
     *
     * @param userName
     * @param id
     * @return
     */
    @DeleteMapping("/{userName}/delete/{id}")
    public ResponseEntity<String> deleteingTweet(@PathVariable String userName,@PathVariable String id){
        logger.info("deleteing tweet");
        return new ResponseEntity<>(tweetServiceImpl.removeTweet(userName,id),HttpStatus.OK);
    }

    /**
     *
     * @param userName
     * @param id
     * @return
     */

    @GetMapping("/{userName}/like/{id}")
    public ResponseEntity<Tweet> addlike(@PathVariable String userName,@PathVariable String id){
        logger.info("{} added like to the post",userName);
        return new ResponseEntity<>(tweetServiceImpl.tweetLike(userName,id),HttpStatus.OK);
    }

    /**
     *
     * @param userName
     * @param id
     * @param reply
     * @return
     */
    @PostMapping("/{userName}/reply/{id}")
    public ResponseEntity<Tweet> replyingTweet(@PathVariable String userName, @PathVariable String id, @RequestBody JsonNode reply){
       logger.info("{} replied to your post ",userName);
        return new  ResponseEntity<>(tweetServiceImpl.addingReply(userName,id,reply),HttpStatus.OK);
    }

}
