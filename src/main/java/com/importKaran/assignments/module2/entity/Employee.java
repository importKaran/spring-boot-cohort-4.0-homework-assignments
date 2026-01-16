package com.importKaran.assignments.module2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employee_module2")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
