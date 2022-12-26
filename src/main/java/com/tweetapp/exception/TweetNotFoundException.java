package com.tweetapp.exception;

public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException(String s) {
        super(s);
    }
}
