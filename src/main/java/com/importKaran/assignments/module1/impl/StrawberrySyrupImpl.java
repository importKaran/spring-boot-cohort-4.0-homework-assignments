package com.importKaran.assignments.module1.impl;

import com.importKaran.assignments.module1.interfaces.Syrup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "syrup.type", havingValue = "strawberry")
public class StrawberrySyrupImpl implements Syrup {

    @Override
    public String getSyrupType() {
        return "Strawberry Syrup!";
    }
}
