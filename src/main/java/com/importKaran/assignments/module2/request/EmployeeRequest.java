package com.importKaran.assignments.module2.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeRequest {

    @Min(value = 18L, message = "Employee cannot be minor, i.e., less than 18 years of age!")
    @Max(value = 60L, message = "Employee cannot be retired, i.e., more than 60 years of age!")
    private Integer age;

    @DecimalMin(value = "10000.00", message = "Employee can not have salary less than 10k!")
    @DecimalMax(value = "1000000.00", message = "Employee can not have salary more than 10L!")
    private Double salary;

    @NegativeOrZero(message = "Employee can either have zero penalty, or negative penalty!")
    private Double penalty;

    @PositiveOrZero(message = "Employee can have either 0% hike or some positive % of hike only!")
    private Double hikeOnPreviousSalary;

    @Size(min = 1, message = "Atleast 1 unique phone should be associated with the Employee!")
    private Set<
            @Digits(integer = 10, fraction = 0, message = "Phone number should be exactly 10 digits, no decimal, no other " +
            "characters!") String
            > phoneNumbers;

    @PastOrPresent(message = "Date of joining can either be in past or today, not in future!")
    private LocalDate joiningDate;

    @Pattern(regexp = "^[a-zA-Z]+$|^[a-zA-Z]+ [a-zA-Z]+$", message = "Employee Name can only be in format like 'Adam'" +
            " or 'Adam Smith', no special characters, no more than 1 space, and that too between first name and last " +
            "name and no numbers!")
    private String name;

    @Email
    private String email;

    @Length(min = 16, max = 16, message = "Credit card should contain exactly 16 digits, no more, no less!")
    @Digits(integer = 16, fraction = 0, message = "Credit card can't have alphabets, special characters nor decimal!")
    @CreditCardNumber
    private String primaryCreditCardNumber;
}
