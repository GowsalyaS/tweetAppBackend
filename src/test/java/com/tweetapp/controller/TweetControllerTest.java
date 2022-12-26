package com.tweetapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserProfile;
import com.tweetapp.service.TweetAppTweetsServiceImpl;
import com.tweetapp.service.TweetappUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetControllerTest {

    @InjectMocks
    private TweetAppController controller;

    @Mock
    private TweetappUserServiceImpl userImpl;

    @Mock
    private TweetAppTweetsServiceImpl tweetImpl;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addNewUserTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        when(userImpl.registerUser(Mockito.any())).thenReturn(" Successfully Created");
        Assertions.assertEquals( 200,controller.addNewUser(userObj).getStatusCodeValue());
    }

    @Test
    void resetPasswordTest(){
        UserProfile userObj = new UserProfile();
        userObj.setPassword("army");
        when(userImpl.toresetPassword(Mockito.anyString(),Mockito.any())).thenReturn("Password changed successfully");
        Assertions.assertEquals(202,controller.resetPassword("RM",userObj).getStatusCodeValue());
    }

    @Test
    void getAllUserTest(){
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
        when(userImpl.getAllUsers()).thenReturn(listUser);
        Assertions.assertEquals(200,controller.getAllUser().getStatusCodeValue());
    }

    @Test
    void postCommentsTest(){
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        when(tweetImpl.addPost(Mockito.anyString(),Mockito.any())).thenReturn("posted");
        Assertions.assertEquals(200,controller.postComments("RM",tweetobj).getStatusCodeValue());
    }

    @Test
    void getAllTweetsTest(){
        List<Tweet> listTweet=new ArrayList<>();

        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        listTweet.add(tweetobj);
        when(tweetImpl.getTweets()).thenReturn(listTweet);
        Assertions.assertEquals(200,controller.getAllTweets().getStatusCodeValue());
    }

    @Test
    void updateTweetTest(){
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        when(tweetImpl.modifyTweet(Mockito.anyString(),Mockito.anyString(),Mockito.any())).thenReturn(tweetobj);
        Assertions.assertEquals(200,controller.updateTweet("RM","1",tweetobj).getStatusCodeValue());
    }

    @Test
    void getAllTweetTest(){
        List<Tweet> listTweet=new ArrayList<>();

        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        listTweet.add(tweetobj);
        when(tweetImpl.getTweetByName(Mockito.any())).thenReturn(listTweet);
        Assertions.assertEquals(200,controller.getAllTweet(tweetobj).getStatusCodeValue());
    }

    @Test
    void searchByUserNameTest(){
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
        UserProfile userObj1 = new UserProfile();
        userObj1.setLoginid("RM");
        when(userImpl.userNameSearch(Mockito.any())).thenReturn(listUser);
        Assertions.assertEquals(200,controller.searchByUserName(userObj1).getStatusCodeValue());
    }

    @Test
    void deleteTweetTest(){
        when(tweetImpl.removeTweet(Mockito.anyString(),Mockito.anyString())).thenReturn( "Tweet deleted!!...");
        Assertions.assertEquals(200,controller.deleteingTweet("RM","1").getStatusCodeValue());
    }

    @Test
    void addlikeTest(){
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        when(tweetImpl.tweetLike(Mockito.anyString(),Mockito.anyString())).thenReturn(tweetobj);
        Assertions.assertEquals(200,controller.addlike("RM","1").getStatusCodeValue());
    }

    @Test
    void replyingTweetTest() throws JsonProcessingException {
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        String str="{ \"replies\" :\"you know WWH\"} ";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        when(tweetImpl.addingReply(Mockito.anyString(),Mockito.anyString(),Mockito.any())).thenReturn(tweetobj);
        Assertions.assertEquals(200,controller.replyingTweet("RM","1",jsonNode).getStatusCodeValue());
    }
}
