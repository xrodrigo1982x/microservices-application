package com.tweet.application.service.user;

import org.springframework.cache.annotation.Cacheable;

public interface UserService {
    User save(User search);

    User findById(String id);
}
