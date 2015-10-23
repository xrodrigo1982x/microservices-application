package com.tweet.application.controller;

import com.tweet.application.login.UserLoginService;
import com.tweet.application.service.user.User;
import com.tweet.application.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static com.tweet.application.controller.util.ErrorUtil.summarize;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("{id}")
    public ResponseEntity findById(@PathVariable String id){
        return ok(userService.findById(id));
    }

    @RequestMapping(value = "", method = POST)
    public ResponseEntity save(@RequestBody @Valid User user, BindingResult result, @AuthenticationPrincipal Principal token){
        if(result.hasErrors()) return new ResponseEntity(summarize(result), HttpStatus.BAD_REQUEST);
        user.setUsername(token.getName());
        return ok(userService.save(user));
    }
}
