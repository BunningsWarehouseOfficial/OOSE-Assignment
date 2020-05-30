package krados.oose.assignment.model;

import krados.oose.assignment.controller.Controller;

public class Dragon extends Enemy {
    //CONSTRUCTOR
    public Dragon() {
        super(100, 15, 30, 15, 20, 100);
    }

    //ACCESSORS
    public String getSpecies() {
        return "Dragon";
    }

    //OTHER
    @Override
    public double ability(double damage) {
        double outDamage = damage;
        double prob = Math.random();
        if (prob < 0.25) {
            outDamage *= 2.0;
            setUsedAbility("Double Damage (+ " + Controller.cleanDouble(outDamage - damage) + " damage)");
        }
        else if (prob < 0.1) {
            double newHealth = Math.max(getMaxHealth(), getHealth() + 10.0);
            setHealth(newHealth);
            setUsedAbility("Recover Health (+ 10 health)");
        }
        return outDamage;
    }
}
