package krados.oose.assignment.model;

import krados.oose.assignment.controller.Controller;

import java.util.Random;

public class Ogre extends Enemy {
    public static final double INITIAL_PROB = 0.2;

    //CONSTRUCTOR
    public Ogre() {
        super(40, 5, 10, 6, 12, 40);
    }

    //ACCESSORS
    public String getSpecies() {
        return "Ogre";
    }

    //OTHER
    @Override
    public double ability(double damage) {
        double outDamage = damage;
        if (Math.random() < 0.2) {
            Random rand = new Random();
            outDamage += (double)rand.nextInt(getMaxDamage() - getMinDamage() + 1) + getMinDamage();
            setUsedAbility("Attack Twice (+ " + Controller.cleanDouble(outDamage - damage) + " damage)");
        }
        return outDamage;
    }
}
