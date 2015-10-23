package com.tweet.application.controller.util;

import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

public class ErrorUtil {

    public static Map<String, String> summarize(BindingResult result){
        return result.getFieldErrors().parallelStream()
                .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));
    }

}
