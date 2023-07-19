package com.example.exception_handling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    PARAM_NOT_FOUND(HttpStatus.NOT_FOUND,"파라미터 확인"),
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND,"404 NOT FOUND"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"허용되지 않은 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 오류 입니다."),
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED,"인증 오류")
    ;

    private final HttpStatus status;//HTTP 상태코드를 상수로 선언해둔 HttpStatus 타입의 멤버로 예외에 대한 상태코드와 이름 처리하는데 사용
    private final String message;//예외에 대한 응답 메시지 처리하는데 사용되는 멤버

}
