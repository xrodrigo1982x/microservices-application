package com.tweet.application.login;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface LoginRepository extends MongoRepository<UserLogin, String> {

    UserLogin findByEmail(String email);

}
