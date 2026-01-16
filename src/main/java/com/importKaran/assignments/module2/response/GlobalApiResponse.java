package com.importKaran.assignments.module2.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.importKaran.assignments.module2.error.GlobalApiError;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GlobalApiResponse<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
    private LocalDateTime timestamp;
    private T data;
    private GlobalApiError error;

    public GlobalApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public GlobalApiResponse(T data) {
        this();
        this.data = data;
    }

    public GlobalApiResponse(GlobalApiError error) {
        this();
        this.error = error;
    }
}
