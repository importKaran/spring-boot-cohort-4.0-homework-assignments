package com.importKaran.assignments.module2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurator {

    @Bean
    public ModelMapper configureModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        return modelMapper;
    }

    @Bean
    public ObjectMapper configureObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

//                Also, if we don't register JavaTimeModule, then we will get errors that ObjectMapper is unable to
//                serialize timestamp, as by default it doesn't have it
        objectMapper.registerModule(new JavaTimeModule());

//                If we don't disable this feature, then by default, the ObjectMapper will convert the timestamp in
//                following format
//                "timestamp": { "nano": 936173500, "year": 2026, "monthValue": 1, "dayOfMonth": 16, "hour": 21, "minute": 9, "second": 40, "month": "JANUARY", "dayOfWeek": "FRIDAY", "dayOfYear": 16, "chronology": { "id": "ISO", "calendarType": "iso8601", "isoBased": true } }
//                So to avoid this, we have to disable this feature
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
