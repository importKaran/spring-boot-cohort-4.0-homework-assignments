package com.importKaran.assignments.module2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.importKaran.assignments.module2.entity.Department;
import com.importKaran.assignments.module2.error.ResourceNotFoundException;
import com.importKaran.assignments.module2.repository.DepartmentRepo;
import com.importKaran.assignments.module2.request.CreateDepartmentRequest;
import com.importKaran.assignments.module2.request.UpdateDepartmentRequest;
import com.importKaran.assignments.module2.response.DepartmentResponse;
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
public class DepartmentBusinessLogic {

    @Autowired
    DepartmentRepo repo;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {

        List<Department> departmentList = repo.findAll();

        if(departmentList.isEmpty())
            throw new ResourceNotFoundException("Department List is empty!");

        List<DepartmentResponse> responseList = departmentList
                .stream()
                .map(department -> modelMapper.map(department, DepartmentResponse.class))
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.FOUND);
    }

    public ResponseEntity<DepartmentResponse> getDepartmentById(Integer id) {

        Department department = repo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Department found for ID : " + id));

        return new ResponseEntity<>(modelMapper.map(department, DepartmentResponse.class), HttpStatus.FOUND);
    }

    public ResponseEntity<DepartmentResponse> createNewDepartment(CreateDepartmentRequest request) {

        Department department = modelMapper.map(request, Department.class);
        repo.save(department);
        return new ResponseEntity<>(modelMapper.map(department, DepartmentResponse.class), HttpStatus.CREATED);
    }

    public ResponseEntity<DepartmentResponse> updateDepartmentPut(Integer id, UpdateDepartmentRequest request) {

        if(!repo.existsById(id))
            throw new ResourceNotFoundException("No Department present with this ID : " + id);

        if(!request.getId().equals(id))
            throw new IllegalArgumentException("IDs in both, URL and RequestBody are different!");

        Department department = modelMapper.map(request, Department.class);
        repo.save(department);
        return new ResponseEntity<>(modelMapper.map(department, DepartmentResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<DepartmentResponse> updateDepartmentPatch(Integer id, Map<String, Object> fieldsToUpdate) {

        ObjectMapper objectMapper = new ObjectMapper();

        Department department = repo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Department present with this ID : " + id));

        fieldsToUpdate
                .forEach((fieldName, fieldValue) -> {
                    Field field = ReflectionUtils.getRequiredField(Department.class, fieldName);
                    field.setAccessible(true);

//                    To even set LocalDate, without any explicit conversion
                    Object fieldValueToBeSet = objectMapper
                            .convertValue(fieldValue, objectMapper
                                    .getTypeFactory()
                                    .constructType(field.getType()));

                    ReflectionUtils.setField(field, department, fieldValueToBeSet);
                });

        repo.save(department);
        return new ResponseEntity<>(modelMapper.map(department, DepartmentResponse.class), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteDepartment(Integer id) {

        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Department present with this ID : " + id));
        repo.deleteById(id);
        return new ResponseEntity<>("Department having ID : " + id + " is deleted successfully!", HttpStatus.OK);
    }
}
