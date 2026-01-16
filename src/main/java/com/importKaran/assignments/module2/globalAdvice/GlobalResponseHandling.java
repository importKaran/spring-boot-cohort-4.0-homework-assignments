package com.importKaran.assignments.module2.globalAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.importKaran.assignments.module2.response.GlobalApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandling implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;    // making this true will enable spring boot to use the below, beforeBodyWrite method to intervene
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(body instanceof GlobalApiResponse<?>)
            return body;

//        If we are returning String or ResponseEntity<String> then that case needs to be handled differently
//        As this is being handled by StringHttpMessageConverter, which needs to be handled in a different way
        if(body instanceof String) {
            try {
                return objectMapper.writeValueAsString(new GlobalApiResponse<>(body));
            } catch (JsonProcessingException e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

//        Since all the exceptions are already made in GlobalApiResponse using GlobalExceptionHandler
//        And if some response is already an instance of GlobalApiResponse, it is returned as it is
//        So now, just we have to make sure, the response which is not an instance of GlobalApiResponse,
//        We need to convert that too (example: ResponseEntity<EmployeeResponse>)
        return new GlobalApiResponse<>(body);
    }
}
