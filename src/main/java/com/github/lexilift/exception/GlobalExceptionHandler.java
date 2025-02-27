package com.github.lexilift.exception;

import com.github.lexilift.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response illegalArgument(IllegalArgumentException exception) {
        return Response.error(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public Response illegalState(IllegalStateException exception) {
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(Exception exception) {
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
