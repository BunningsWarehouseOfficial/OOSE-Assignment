package krados.oose.assignment.model;

import krados.oose.assignment.controller.Controller;

public class Slime extends Enemy {
    public static final double INITIAL_PROB = 0.5;

    //CONSTRUCTOR
    public Slime() {
        super(10, 3, 5, 0, 2, 10);
    }

    //ACCESSORS
    public String getSpecies() {
        return "Slime";
    }

    //OTHER
    @Override
    public double ability(double damage) {
        double outDamage = damage;
        if (Math.random() < 0.2) {
            outDamage = 0.0;
            setUsedAbility("Miss Attack (- " + (Controller.cleanDouble(damage)) + " damage)");
        }
        return outDamage;
    }
}
