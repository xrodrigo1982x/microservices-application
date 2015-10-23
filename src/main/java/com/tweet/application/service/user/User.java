package com.tweet.application.service.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private String username;
    private String name;

}
