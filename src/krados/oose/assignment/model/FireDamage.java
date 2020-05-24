package krados.oose.assignment.model;

import java.util.Random;

public class FireDamage extends Enchantment {
    public FireDamage(Enchantable next) {
        super("Fire Damage", 20);
        this.next = next;
    }

    @Override
    public double attack() {
        Random rand = new Random();
        int minDamage = 5;
        int maxDamage = 10;

        double extraDamage = (double)rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        return next.attack() + extraDamage;
    }
}
