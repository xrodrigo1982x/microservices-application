package com.tweet.application.login;

import com.tweet.application.login.exception.UserAlreadyExistsException;

public interface LoginService {

    void create(UserLogin userLogin) throws UserAlreadyExistsException;

}
