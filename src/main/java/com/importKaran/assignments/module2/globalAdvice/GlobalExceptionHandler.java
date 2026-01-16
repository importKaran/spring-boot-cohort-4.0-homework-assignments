package com.importKaran.assignments.module2.globalAdvice;

import com.importKaran.assignments.module2.error.GlobalApiError;
import com.importKaran.assignments.module2.error.ResourceNotFoundException;
import com.importKaran.assignments.module2.response.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException error) {
        GlobalApiError apiError = GlobalApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(error.getMessage())
                .build();
        return createErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleValidAnnotationException(MethodArgumentNotValidException error) {

        List<String> errors = error
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + " : " + x.getDefaultMessage())
                .toList();
        GlobalApiError apiError = GlobalApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Request Body did not pass all validations!")
                .subErrors(errors)
                .build();
        return createErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalApiResponse<?>> handleGenericException(Exception error) {
        GlobalApiError apiError = GlobalApiError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(error.getMessage())
                .build();
        return createErrorResponseEntity(apiError);
    }

    private ResponseEntity<GlobalApiResponse<?>> createErrorResponseEntity(GlobalApiError apiError) {
        return new ResponseEntity<>(new GlobalApiResponse<>(apiError), apiError.getStatus());
    }
}
