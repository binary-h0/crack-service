package com.kt.global.exception;

import org.springframework.http.HttpStatus;

import com.kt.global.dto.Result;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    protected final HttpStatus httpStatus;
    private final Result<?> result;

    public CustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.result = Result.fail(message);
    }
}
