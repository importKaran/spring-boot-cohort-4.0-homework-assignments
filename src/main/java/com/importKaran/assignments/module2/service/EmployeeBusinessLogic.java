package com.importKaran.assignments.module2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.importKaran.assignments.module2.entity.Employee;
import com.importKaran.assignments.module2.error.ResourceNotFoundException;
import com.importKaran.assignments.module2.repository.EmployeeRepo;
import com.importKaran.assignments.module2.request.CreateEmployeeRequest;
import com.importKaran.assignments.module2.request.UpdateEmployeeRequest;
import com.importKaran.assignments.module2.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeBusinessLogic {

    @Autowired
    EmployeeRepo repo;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

        List<Employee> employeeList = repo.findAll();

        if(employeeList.isEmpty())
            throw new ResourceNotFoundException("Employee List is empty!");

        List<EmployeeResponse> responseList = employeeList
                .stream()
                .map(department -> modelMapper.map(department, EmployeeResponse.class))
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.FOUND);
    }

    public ResponseEntity<EmployeeResponse> getEmployeeById(Integer id) {

        Employee employee = repo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee found for ID : " + id));

        return new ResponseEntity<>(modelMapper.map(employee, EmployeeResponse.class), HttpStatus.FOUND);
    }

    public ResponseEntity<EmployeeResponse> createNewEmployee(CreateEmployeeRequest request) {

        Employee employee = modelMapper.map(request, Employee.class);
        repo.save(employee);
        return new ResponseEntity<>(modelMapper.map(employee, EmployeeResponse.class), HttpStatus.CREATED);
    }

    public ResponseEntity<EmployeeResponse> updateEmployeePut(Integer id, UpdateEmployeeRequest request) {

        if(!repo.existsById(id))
            throw new ResourceNotFoundException("No Employee present with this ID : " + id);

        if(id != request.getId())
            throw new IllegalArgumentException("IDs in both, URL and RequestBody are different!");

        Employee employee = modelMapper.map(request, Employee.class);
        repo.save(employee);
        return new ResponseEntity<>(modelMapper.map(employee, EmployeeResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<EmployeeResponse> updateEmployeePatch(Integer id, Map<String, Object> fieldsToUpdate) {

        ObjectMapper objectMapper = new ObjectMapper();

        Employee employee = repo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee present with this ID : " + id));

        fieldsToUpdate
                .forEach((fieldName, fieldValue) -> {
                    Field field = ReflectionUtils.getRequiredField(Employee.class, fieldName);
                    field.setAccessible(true);

//                    To even set LocalDate, without any explicit conversion
                    Object fieldValueToBeSet = objectMapper
                            .convertValue(fieldValue, objectMapper
                                    .getTypeFactory()
                                    .constructType(field.getType()));

                    ReflectionUtils.setField(field, employee, fieldValueToBeSet);
                });

        repo.save(employee);
        return new ResponseEntity<>(modelMapper.map(employee, EmployeeResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEmployee(Integer id) {

        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Employee present with this ID : " + id));
        repo.deleteById(id);
        return new ResponseEntity<>("Employee having ID : " + id + " is deleted successfully!", HttpStatus.OK);
    }
}
