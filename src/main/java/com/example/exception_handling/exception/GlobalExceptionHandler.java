package com.example.exception_handling.exception;

import com.example.exception_handling.dto.ErrorCode;
import com.example.exception_handling.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice//전역 예외 처리(컨트롤러 전역에서 발생할 수 있는 예외 잡아서 Throw)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {//추상 클래스
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //@ResponseEntityExceptionHandler : 스프링 예외에 대한 ExceptionHandler 모두 구현되어 있어 상속

    /*
     * handleExceptionInternal() 메소드를 오버라이딩해 응답 커스터마이징
     */
    private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode){
        System.out.println("handleExceptionInternal: {} = " + errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatus().value())
                .body(new ErrorResponse(errorCode));
    }

    /*
    * Developer Custom Exception : 직접 정의한 RestApiException 에러 클래스에 대한 예외 처리
    */
    @ExceptionHandler(RestApiException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(RestApiException ex){
        System.out.println("handleCustomException: {} = " + ex.getErrorCode());
        ErrorCode errorCode = ex.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalException(IllegalArgumentException ex){
        System.out.println("handleIllegalException: {} = " + ex.getMessage());
        ErrorCode errorCode = ErrorCode.PARAM_NOT_FOUND;
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception e){
        System.out.println("request = " + request.getRequestURI());
        System.out.println("request(query) = " + request.getQueryString());
        System.out.println("handleException : {} = " + e.getMessage());
        logger.info("handleException : {} = " + e.getMessage());
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e){
        System.out.println("handleNullPointerException : {} = " + e.getMessage());
        return ResponseEntity.status(ErrorCode.BAD_REQUEST.getStatus().value())
                .body(new ErrorResponse(ErrorCode.BAD_REQUEST));
    }



}
