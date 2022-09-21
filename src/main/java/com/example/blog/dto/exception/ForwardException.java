package com.example.blog.dto.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ForwardException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}
