package com.example.exception_handling.controller;


import com.example.exception_handling.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @Autowired
    ExceptionService exceptionService;

    @GetMapping(value = "/api/br")
    public String exceptionServer(@RequestParam(value = "str", required = false) String str) throws Exception {
        int a = 3 / 0;
        return str;
    }

    @GetMapping(value = "/api/sr")
    public String exceptionTryCatch() throws Exception{
        String tryCatchException = exceptionService.tryCatchException();
        return tryCatchException;
    }
}
