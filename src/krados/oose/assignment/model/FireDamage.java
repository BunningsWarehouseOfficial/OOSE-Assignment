package krados.oose.assignment.model;

import java.util.Random;

public class FireDamage extends Enchantment {
    public static int COST = 20;
    public static int MIN_EXTRA = 5;
    public static int MAX_EXTRA = 10;

    //CONSTRUCTOR
    public FireDamage(Weapon next) {
        super(next);
    }

    //ACCESSORS

    @Override
    public int getCost() {
        return next.getCost() + COST;
    }
    @Override
    public double getMinDamage() {
        return next.getMinDamage() + MIN_EXTRA;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() + MAX_EXTRA;
    }

    //OTHER
    @Override
    public double attack() {
        Random rand = new Random();
        double extraDamage = (double)rand.nextInt(MAX_EXTRA - MIN_EXTRA + 1) + MIN_EXTRA;
        return next.attack() + extraDamage;
    }
}
