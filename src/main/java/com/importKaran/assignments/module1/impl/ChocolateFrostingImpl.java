package com.importKaran.assignments.module1.impl;

import com.importKaran.assignments.module1.interfaces.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "frosting.type", havingValue = "chocolate")
public class ChocolateFrostingImpl implements Frosting {

    @Override
    public String getFrostingType() {
        return "Chocolate Frosting!";
    }
}
