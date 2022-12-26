package com.tweetapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.Like;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UserProfile;
import com.tweetapp.repo.TweetsRepo;
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
import java.util.*;

@ExtendWith(MockitoExtension.class)
 class TweetAppTweetsServiceImplTest {

    @InjectMocks
    private TweetAppTweetsServiceImpl tweetService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private TweetsRepo tweetRepo;


    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void addPostTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));

        String str=tweetService.addPost("RM",tweetobj);
        Assertions.assertEquals("I'm rap monster(posted)",str) ;
    }

    @Test
     void addPostNotFoundTest() {
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->tweetService.addPost("RM",tweetobj));
    }
    @Test
     void getAllTweetsTest(){
        List<Tweet> listTweet=new ArrayList<>();
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        listTweet.add(tweetobj);
        Mockito.when(tweetRepo.findAll()).thenReturn(listTweet);
        Assertions.assertEquals(1,tweetService.getTweets().size());
    }

    @Test
     void modifyTweetTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.of(tweetobj));
        Tweet modifedTweet=new Tweet();
        modifedTweet.setTweets("I'm Leader");
        Tweet expected=tweetService.modifyTweet("RM","1",modifedTweet);
        Assertions.assertEquals(expected.getTweets(),modifedTweet.getTweets());
    }

    @Test
     void modifyTweetUserNotFoundTest(){
        UserProfile userObj = new UserProfile();
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->tweetService.modifyTweet("RM","1",tweetobj));

    }

    @Test
     void modifyTweetTweetIDExceptionTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(TweetNotFoundException.class,()->tweetService.modifyTweet("RM","1",tweetobj));
    }

    @Test
     void getTweetNameTest(){
        List<Tweet> listTweet=new ArrayList<>();
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setUserName("RM");
        listTweet.add(tweetobj);
        Mockito.when(tweetRepo.findByuserName(Mockito.anyString())).thenReturn(listTweet);
        Assertions.assertEquals(1,tweetService.getTweetByName(tweetobj).size());


    }

    @Test
    void getTweetNameUserNotFoundExceptionTest(){
        List<Tweet> listTweet=null;
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setUserName("RM");
        Mockito. when(tweetRepo.findByuserName(Mockito.anyString())).thenReturn(listTweet);
        Assertions.assertThrows(UserNotFoundException.class,()->tweetService.getTweetByName(tweetobj));
    }
    @Test
     void removeTweetTest(){
        List<Tweet> listTweet=new ArrayList<>();
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setUserName("RM");
        tweetobj.setTweetId("1");
        listTweet.add(tweetobj);
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.of(tweetobj));
        Assertions.assertEquals("Tweet deleted!!...",tweetService.removeTweet("RM","1"));
    }

    @Test
    void removeTweetUserNotFoundExceptionTest(){
        UserProfile userObj = new UserProfile();
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->tweetService.removeTweet("RM","1"));
    }

    @Test
     void removeTweetTweetIdNotFoundExceptionTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(TweetNotFoundException.class,()->tweetService.removeTweet("RM","1"));
    }


    @Test
     void addingReplyUserNotFoundExceptionTest() throws RuntimeException,JsonProcessingException {
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        String str="{ \"replies\" :\"you know WWH\",\"tagged\" :\"WWH\"} ";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        Assertions.assertThrows(UserNotFoundException.class,()->tweetService.addingReply("RM","1", jsonNode));
    }

    @Test
     void addingReplyTweetNotFoundExceptionTest() throws RuntimeException, JsonProcessingException {
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        String str="{ \"replies\" :\"you know WWH\",\"tagged\" :\"WWH\"} ";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        Assertions.assertThrows(TweetNotFoundException.class,()->tweetService.addingReply("RM","1", jsonNode));
    }

    @Test
    void tweetLikeTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.of(tweetobj));
        Assertions.assertEquals(1,tweetService.tweetLike("RM","1").getLikedby().size());
    }

    @Test
    void tweetLikeUserNotFoundTest(){
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->tweetService.tweetLike("RM","1"));
    }

    @Test
    void tweetLikeTweetNotFoundTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(TweetNotFoundException.class,()->tweetService.tweetLike("RM","1"));
    }

    @Test
    void tweetMultipleLikeTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        List<Like> likeData=new LinkedList<>();

        Like likeobj=new Like();
        Map<String,Boolean> mapObj=new HashMap<>();
        mapObj.put("JIN",true);
        likeobj.setUserliked(mapObj);
        likeData.add(likeobj);
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        tweetobj.setLikedby(likeData);
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.of(tweetobj));
        Assertions.assertEquals(2,tweetService.tweetLike("RM","1").getLikedby().size());
    }

    @Test
    void tweetDisLikeTest(){
        UserProfile userObj = new UserProfile();

        userObj.setLoginid("RM");
        userObj.setFristname("Kim");
        userObj.setLastname("Namjoon");
        userObj.setEmail("rm@bts.com");
        userObj.setPassword("army");
        userObj.setConfirmpassword("army");
        userObj.setContactnumber(1234567890l);
        Tweet tweetobj=new Tweet();
        List<Like> likeData=new LinkedList<>();

        Like likeobj=new Like();
        Map<String,Boolean> mapObj=new HashMap<>();
        mapObj.put("RM",true);
        likeobj.setUserliked(mapObj);
        likeData.add(likeobj);
        tweetobj.setTweets("I'm rap monster");
        tweetobj.setTweetId("1");
        tweetobj.setLikedby(likeData);
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(userObj));
        Mockito.when(tweetRepo.findById(Mockito.anyString())).thenReturn(Optional.of(tweetobj));
        Assertions.assertEquals(0,tweetService.tweetLike("RM","1").getLikedby().size());
    }


}
