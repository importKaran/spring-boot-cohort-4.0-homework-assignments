package com.importKaran.assignments.module2.h2DB.workingExample;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {

    private String errorMessage;
    private HttpStatus status;
    private List<String> subErrors;
}
