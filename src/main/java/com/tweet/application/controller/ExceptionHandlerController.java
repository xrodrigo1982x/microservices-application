package com.tweet.application.controller;

import feign.FeignException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Log LOG = LogFactory.getLog(ExceptionHandlerController.class);

    @ExceptionHandler
    public ResponseEntity handleError(Exception e) {
        LOG.error("Error", e);
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler
    public ResponseEntity handleFeignError(FeignException e) {
        LOG.error(e.getMessage(), e);
        if(e.getMessage().contains("status 404")) return new ResponseEntity(NOT_FOUND);
        return handleError(e);
    }
}