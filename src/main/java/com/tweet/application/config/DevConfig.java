package com.tweet.application.config;

import com.tweet.application.service.tweet.Tweet;
import com.tweet.application.service.tweet.TweetPage;
import com.tweet.application.service.user.User;
import com.tweet.application.service.tweet.TweetService;
import com.tweet.application.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.Date;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public TweetService tweetRepository(){
        return new TweetService() {

            private Map<String, Tweet> tweets = new HashMap<>();

            @Override
            public Tweet findById(@PathVariable("id") String id) {
                return tweets.get(id);
            }

            @Override
            public Tweet save(@PathVariable("user") String user, Tweet search) {
                search.setUser(user);
                Date date = new Date();
                date.setHours(date.getHours() - 3);
                search.setDate(date);
                if(search.getId() == null) search.setId(UUID.randomUUID().toString());
                tweets.put(search.getId(), search);
                return search;
            }

            @Override
            public TweetPage timeline(@PathVariable("user") String user, @RequestParam("page") Integer page) {
                return TweetPage.builder().content(new ArrayList<>(tweets.values())).build();
            }
        };
    }

    @Bean
    public UserService userRepository(){
        return new UserService() {

            private Map<String, User> users = new HashMap<>();

            @Override
            public User findById(@PathVariable("id") String id) {
                return users.get(id);
            }

            @Override
            public User save(User user) {
                users.put(user.getUsername(), user);
                return user;
            }

        };
    }

}
