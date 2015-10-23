package com.tweet.application.login;

import com.tweet.application.login.exception.UserAlreadyExistsException;
import com.tweet.application.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserLoginService implements UserDetailsService, LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin one = loginRepository.findOne(username);
        if(one == null) throw new UsernameNotFoundException(username);
        return one;
    }

    @Override
    public void create(UserLogin userLogin) throws UserAlreadyExistsException {
        if(loginRepository.findOne(userLogin.getUsername()) != null || loginRepository.findByEmail(userLogin.getEmail()) != null){
            throw new UserAlreadyExistsException();
        }
        userLogin.setEnabled(true);
        userLogin.setAccountNonLocked(true);
        loginRepository.save(userLogin);
    }
}
