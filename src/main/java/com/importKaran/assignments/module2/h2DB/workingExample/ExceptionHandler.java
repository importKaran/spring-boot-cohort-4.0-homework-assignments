package com.importKaran.assignments.module2.h2DB.workingExample;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Data
public class ExceptionHandler {

//    Exception handling for Employee not found
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GlobalResponse<?>> resourceNotFound(NoSuchElementException exception) {
        ApiError error = ApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .errorMessage(exception.getMessage())
                .build();
        return createErrorResponseEntity(error);
    }

//    Exception handling for fields, failing for @Valid Annotation, including the custom, @NameValidation
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<?>> fieldNotValid(MethodArgumentNotValidException exception) {

//        One way of directly getting fields failing to comply, and their error messages separately (.getFieldErrors())
//        Map<String, String> errors = new HashMap<>();
//        exception
//                .getBindingResult()
//                .getFieldErrors()
//                .forEach(error ->
//                        errors.put(error.getField(), error.getDefaultMessage()));

//        Another way of getting all the errors and collecting them in a list (.getAllErrors())
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getCodes()[1].split("\\.")[1] + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError error = ApiError
                .builder()
                .errorMessage("Not passing Request Body Validations!")
                .status(HttpStatus.BAD_REQUEST)
                .subErrors(errors)
                .build();
        return createErrorResponseEntity(error);
    }

    private ResponseEntity<GlobalResponse<?>> createErrorResponseEntity(ApiError error) {
        return new ResponseEntity<>(new GlobalResponse<>(error), error.getStatus());
    }
}
