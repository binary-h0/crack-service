package com.kt.crack.global.exception;

import org.springframework.http.HttpStatus;

import com.kt.crack.global.dto.Result;

import lombok.Getter;

@Getter
public class BussinessException extends RuntimeException {
    protected final HttpStatus httpStatus;
    private final Result<?> result;

    public BussinessException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.result = Result.fail(message);
    }
}
