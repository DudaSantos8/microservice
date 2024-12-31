package br.com.zup.consumer.infra.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ConsumerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, Object> handleExceptionValidationsGrouped(MethodArgumentNotValidException exception) {
        Map<String, List<String>> fieldErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            fieldErrors.computeIfAbsent(field, key -> new ArrayList<>()).add(message);
        });

        return Map.of(
                "message", "Validation failed for the request",
                "errors", fieldErrors
        );
    }

}
