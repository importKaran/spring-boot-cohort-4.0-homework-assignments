package com.importKaran.assignments.module1.runner;

import com.importKaran.assignments.module1.core.CakeBaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CakeBakerRunner implements CommandLineRunner {

//    Dependency Injection - Field Injection
    @Autowired
    CakeBaker cakeBaker;

    @Override
    public void run(String... args) throws Exception {
        cakeBaker.bakeCake();
    }
}
