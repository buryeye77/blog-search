package com.example.blog.platform.exception;

import com.example.blog.dto.exception.CustomException;
import com.example.blog.dto.exception.ErrorResponse;
import com.example.blog.dto.exception.ForwardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(ForwardException.class)
    protected ResponseEntity<ErrorResponse> handleForwardException(final ForwardException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorResponse(e.getHttpStatus(), e.getMessage()));
    }
}
