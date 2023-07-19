package com.example.exception_handling.controller;


import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping(value = "/api/br")
    public String exceptionServer(@RequestParam(value = "str", required = false) String str) throws Exception {
        int a = 3 / 0;
        return str;
    }
}
