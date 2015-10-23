package com.tweet.application.service.tweet;

public interface TweetService {
    Tweet save(String user, Tweet tweet);

    Tweet findById(String id);

    TweetPage timeline(String user, Integer page);
}
