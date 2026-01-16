package com.importKaran.assignments.module2.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeResponse {

    private Integer id;
    private Boolean isEmployeeActive;
    private Integer age;
    private Double salary;
    private Double penalty;
    private Double hikeOnPreviousSalary;
    private Set<String> phoneNumbers;
    private LocalDate joiningDate;
    private String name;
    private String email;
    private String primaryCreditCardNumber;
}
