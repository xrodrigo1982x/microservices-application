package com.tweet.application.service.impl;

import com.tweet.application.config.DefaultProfile;
import com.tweet.application.service.user.User;
import com.tweet.application.repository.UserRepository;
import com.tweet.application.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@DefaultProfile
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User search) {
        return userRepository.save(search);
    }

    @Override
    @Cacheable(value = "application.user", key = "#id")
    public User findById(String id) {
        return userRepository.findById(id);
    }

}
