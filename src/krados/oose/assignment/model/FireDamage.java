package krados.oose.assignment.model;

import java.util.Random;

public class FireDamage extends Enchantment {
    public static int MIN_EXTRA = 5;
    public static int MAX_EXTRA = 10;

    public FireDamage(Enchantable next) {
        super("Fire Damage", 20);
        this.next = next;
    }

    @Override
    public double getMinDamage() {
        return next.getMinDamage() + (double)MIN_EXTRA;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() + (double)MAX_EXTRA;
    }

    @Override
    public double attack() {
        Random rand = new Random();
        double extraDamage = (double)rand.nextInt(MAX_EXTRA - MIN_EXTRA + 1) + MIN_EXTRA;
        return next.attack() + extraDamage;
    }
}
