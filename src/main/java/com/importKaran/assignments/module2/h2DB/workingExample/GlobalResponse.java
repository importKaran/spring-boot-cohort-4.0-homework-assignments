package com.importKaran.assignments.module2.h2DB.workingExample;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GlobalResponse<T> {

    private LocalDateTime timestamp;
    private T data;
    private ApiError error;

    public GlobalResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public GlobalResponse(T data) {
        this();
        this.data = data;
    }

    public GlobalResponse(ApiError error) {
        this();
        this.error = error;
    }
}
