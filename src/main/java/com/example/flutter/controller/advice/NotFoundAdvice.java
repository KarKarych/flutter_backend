package com.example.flutter.controller.advice;

import com.example.flutter.model.basic.ErrorModel;
import com.example.flutter.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        var errorModel = new ErrorModel(e.getClass().getSimpleName(), e.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(errorModel);
    }
}
