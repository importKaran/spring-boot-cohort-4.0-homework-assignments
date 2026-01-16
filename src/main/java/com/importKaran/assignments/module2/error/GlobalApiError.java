package com.importKaran.assignments.module2.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GlobalApiError {

    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
