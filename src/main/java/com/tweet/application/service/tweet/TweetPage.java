package com.tweet.application.service.tweet;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetPage {

    private List<Tweet> content;
    private Long totalElements;
    private Long totalPages;
    private boolean first;
    private Long numberOfElements;
    private boolean last;
    private Long size;
    private Long number;

}
