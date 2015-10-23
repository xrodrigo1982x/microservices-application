package com.tweet.application.service.tweet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    private String id;
    @NotEmpty
    private String content;
    private String user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Map<String, Double> location;

}
