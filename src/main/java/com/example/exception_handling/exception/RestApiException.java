package com.example.exception_handling.exception;

import com.example.exception_handling.dto.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    //발생한 예외 처리해줄 에러 클래스 추가
    //RuntimeException을 상속받아 언체크 예외로 활용
    private final ErrorCode errorCode;
}
