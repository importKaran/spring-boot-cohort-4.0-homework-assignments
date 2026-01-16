package com.importKaran.assignments.module2.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
public class DepartmentRequest {

    @NotBlank(message = "Department Title can't be null or blank!")
    @Size(min = 3, max = 100, message = "Department Title should have minimum 3 characters, and a maximum of 100 " +
            "characters")
    private String title;

    @PastOrPresent
    private LocalDate departmentCreationDate;

    @FutureOrPresent
    private LocalDate departmentEndDate;

    @AssertTrue
    private Boolean isDepartmentActive;

    @URL(protocol = "https", host = "github.com", message = "Department URL should be having secure 'https' protocol, and also only in 'github.com' domain")
    private String departmentURL;
}
