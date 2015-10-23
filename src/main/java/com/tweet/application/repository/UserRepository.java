package com.tweet.application.repository;

import com.tweet.application.service.user.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient("user")
public interface UserRepository {

    @RequestMapping(value = "/{id}", method = GET)
    User findById(@PathVariable("id") String id);

    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    User save(User user);

}
