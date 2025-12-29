package com.importKaran.assignments.module2.h2DB.workingExample;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
@Service
@NoArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private ObjectMapper objectMapper;

    private final ModelMapper mapper = new ModelMapper();

    public EmployeeResponse createNewEmployee(EmployeeRequest request) {

        EmployeeEntity entity = new EmployeeEntity();
        EmployeeResponse response = new EmployeeResponse();

//        Mapping from request to entity
        mapper.map(request, entity);
//        Saving the mapped entity to DB
        repo.save(entity);
//        Mapping from entity to response
        mapper.map(entity, response);
        return response;
    }

    public EmployeeResponse fetchEmployeeById(Long id) {

        EmployeeResponse response = new EmployeeResponse();
        EmployeeEntity entity = repo.findById(id).orElse(null);
        mapper.map(entity, response);
        return response;
    }

    public List<EmployeeResponse> fetchAllEmployees() {

        List<EmployeeResponse> responseList = new ArrayList<>();
        List<EmployeeEntity> employeeList = repo.findAll();
        if(!employeeList.isEmpty()) {

//            Using Streams
            responseList = employeeList
                    .stream()
                    .map(x -> mapper.map(x, EmployeeResponse.class))
                    .collect(Collectors.toList());

//            Alternative way
//            Type typeOfList = new TypeToken<List<EmployeeResponse>>() {}.getType();
//            responseList = mapper.map(employeeList, typeOfList);
        }
        return responseList;
    }

    public EmployeeResponse updateEmployeeByPut(EmployeeRequest requestBody) {

        EmployeeEntity entity = repo.findById(requestBody.getId()).orElseThrow(NullPointerException::new);
        EmployeeResponse response = new EmployeeResponse();

//        Mapping from request to entity
        mapper.map(requestBody, entity);
//        Saving the mapped entity to DB
        repo.save(entity);
//        Mapping from entity to response
        mapper.map(entity, response);
        return response;
    }

    public EmployeeResponse updateEmployeeByPatch(Map<String, Object> fieldsToBeUpdated, Long id){

        EmployeeEntity entity = repo.findById(id).orElse(null);
        if(entity == null)
            return null;

        fieldsToBeUpdated
                .forEach((fieldName, fieldValue) -> {
                    Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, fieldName);
                    fieldToBeUpdated.setAccessible(true);

//                    This will take care of primitive types, as well as non-primitive
//                    Including LocalDate conversion
                    Object fieldValueAfterConvertingToCorrectType =
                            objectMapper.convertValue(fieldValue, objectMapper
                                                    .getTypeFactory()
                                                    .constructType(fieldToBeUpdated.getType()));
                    ReflectionUtils.setField(fieldToBeUpdated, entity, fieldValueAfterConvertingToCorrectType);
                });

        return mapper.map(repo.save(entity), EmployeeResponse.class);
    }

    public ResponseEntity<HttpStatus> deleteEmployee(Long id) {

        EmployeeEntity entity = repo.findById(id).orElse(null);
        if(entity == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            repo.delete(entity);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
