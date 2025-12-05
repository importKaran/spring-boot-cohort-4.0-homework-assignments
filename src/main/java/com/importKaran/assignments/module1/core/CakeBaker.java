package com.importKaran.assignments.module1.core;

import com.importKaran.assignments.module1.interfaces.Frosting;
import com.importKaran.assignments.module1.interfaces.Syrup;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private final Frosting frosting;
    private final Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Starting to bake the cake...");
        System.out.println("Ingredients are:");
        System.out.println("Frosting : " + frosting.getFrostingType());
        System.out.println("Syrup : " + syrup.getSyrupType());
        System.out.println("Cake will be ready in 5 mins!");
    }
}
