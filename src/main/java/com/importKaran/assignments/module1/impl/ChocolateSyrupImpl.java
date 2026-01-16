package com.importKaran.assignments.module1.impl;

import com.importKaran.assignments.module1.interfaces.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chocolate")
public class ChocolateSyrupImpl implements Syrup {

    @Override
    public String getSyrupType() {
        return "Chocolate Syrup!";
    }
}
