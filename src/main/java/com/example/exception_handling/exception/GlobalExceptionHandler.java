package com.example.exception_handling.exception;

import com.example.exception_handling.dto.ErrorCode;
import com.example.programmers.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice//전역 예외 처리(컨트롤러 전역에서 발생할 수 있는 예외 잡아서 Throw)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {//추상 클래스
    //@ResponseEntityExceptionHandler : 스프링 예외에 대한 ExceptionHandler 모두 구현되어 있어 상속
    @ExceptionHandler(RuntimeException.class)//(특정 클래스에서 발생할 수 있는 예외를 잡아 Throw)
    public String handleRuntimeException(final RuntimeException e){
        return e.getMessage();
    }

    /*
    * Developer Custom Exception : 직접 정의한 RestApiException 에러 클래스에 대한 예외 처리
    */
    @ExceptionHandler(RestApiException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(RestApiException ex){
        ErrorCode errorCode = ex.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    /*
    * handleExceptionInternal() 메소드를 오버라이딩해 응답 커스터마이징
    */
    private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getStatus().value())
                .body(new ErrorResponse(errorCode));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(final Exception e){
//        System.out.println("handleException : {} = " + e.getMessage());
//        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
//                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
//    }



}
