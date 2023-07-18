package com.example.exception_handling.controller;

import com.example.exception_handling.dto.ErrorCode;
import com.example.exception_handling.exception.RestApiException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping(value = "/api/br")
    public String exceptionServer() throws Exception {
        throw new RestApiException(ErrorCode.POSTS_NOT_FOUND);
//        return "200";
    }
}
