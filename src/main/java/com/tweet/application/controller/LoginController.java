package com.tweet.application.controller;

import com.tweet.application.login.LoginService;
import com.tweet.application.login.UserLogin;
import com.tweet.application.login.exception.UserAlreadyExistsException;
import com.tweet.application.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.tweet.application.controller.util.ErrorUtil.summarize;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("conta")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST, value = "criar")
    public ResponseEntity criar(@RequestBody @Valid UserLogin userDetails, BindingResult result){
        if(result.hasErrors()) return new ResponseEntity(summarize(result), HttpStatus.BAD_REQUEST);
        try{
            loginService.create(userDetails);
        }catch (UserAlreadyExistsException e){
            return new ResponseEntity(userDetails, HttpStatus.FOUND);
        }
        userDetails.setPassword(null);
        return ok(userDetails);
    }

}
