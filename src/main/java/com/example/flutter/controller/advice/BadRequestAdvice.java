package com.example.flutter.controller.advice;

import com.example.flutter.model.basic.ErrorModel;
import com.example.flutter.util.exception.BadRequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class BadRequestAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorModel> handleBadRequestException(BadRequestException e) {
        log.error(e.getMessage());
        var errorModel = new ErrorModel(e.getClass().getSimpleName(), e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        String errorMessage = "";

        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            errorMessage = String.format("Invalid value in [%s] field", fieldError.getField());
        }

        var errorModel = new ErrorModel(e.getClass().getSimpleName(), errorMessage);
        return ResponseEntity.status(BAD_REQUEST).body(errorModel);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorModel> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(this::createErrorMessage)
                .collect(Collectors.joining("; "));
        log.error(message);
        var errorModel = new ErrorModel(e.getClass().getSimpleName(), message.trim());
        return ResponseEntity.status(BAD_REQUEST).body(errorModel);
    }

    private <T> String createErrorMessage(ConstraintViolation<T> cv) {
        String fieldName = getFieldName(cv.getPropertyPath().toString());
        String message = fieldName + " " + cv.getMessage();
        return message.trim();
    }

    private String getFieldName(String propertyPath) {
        String[] split = propertyPath.split("\\.");
        if (split.length <= 2) {
            return propertyPath;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < split.length; i++) {
            sb.append(split[i]);
        }
        return sb.toString();
    }
}
