package com.example.exception_handling.service;

import org.springframework.stereotype.Service;

import java.util.zip.DataFormatException;

@Service
public class ExceptionService {

    public String tryCatchException() throws Exception {
        String str = "TryCatchException";
        throw new IllegalArgumentException("111");
//        return str;
    }

}
