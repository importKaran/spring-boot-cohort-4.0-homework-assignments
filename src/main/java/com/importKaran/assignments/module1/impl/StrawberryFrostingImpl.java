package com.importKaran.assignments.module1.impl;

import com.importKaran.assignments.module1.interfaces.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "frosting.type", havingValue = "strawberry")
public class StrawberryFrostingImpl implements Frosting {

    @Override
    public String getFrostingType() {
        return "Strawberry Frosting!";
    }
}
