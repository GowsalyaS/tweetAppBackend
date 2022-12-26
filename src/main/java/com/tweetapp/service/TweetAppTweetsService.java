package com.tweetapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tweetapp.model.Tweet;

import java.util.List;

public interface TweetAppTweetsService {

    public String addPost(String userName, Tweet tweets);

    public List<Tweet> getTweets();

    public Tweet modifyTweet(String userName,String id,Tweet tweets);

    public List<Tweet> getTweetByName(Tweet tweet);

    public String removeTweet(String userName,String id);

    public Tweet tweetLike(String userName,String id);

    public Tweet addingReply(String userName, String id, JsonNode reply);
}
