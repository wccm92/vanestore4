package com.example.secAndRouter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBadRequestException(WebClientResponseException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .message("Error: Bad Request")
                .details(ex.getResponseBodyAsString())
                .build();
        return Mono.just(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
