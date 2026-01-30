package com.google.todo.global.exception;

import com.google.todo.global.response.ApiError;
import com.google.todo.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e){
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.fail(new ApiError("Validation Failed",e.getMessage())));
    }

}
