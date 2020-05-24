package krados.oose.assignment.model;

import java.util.Random;

public class Ogre extends Enemy {
    private double maxHealth;
    private double health;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int reward;

    public Ogre() {
        super(40, 5, 10, 6, 12, 40);
    }

    @Override
    public double ability(double damage) {
        double outDamage = damage;
        if (Math.random() < 0.2) {
            Random rand = new Random();
            outDamage += (double)rand.nextInt(maxDamage - minDamage + 1) + minDamage;
            //TODO signal view ability was used
        }
        return outDamage;
    }
}
