package krados.oose.assignment.model;

import java.util.Random;

public abstract class Enemy extends Entity {
    private int minDamage; //TODO does maxHealth and health need to be here? Seems like I don't
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int reward;

    public Enemy(int maxHealth, int minDamage, int maxDamage, int minDefence, int maxDefence,
                 int reward) {
        super(maxHealth);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.reward = reward;
    }

    abstract public double ability(double damage);

    @Override
    public double attack() { //TODO does this even work being in abstract class? Subclass fields don't do anything
        Random rand = new Random();
        double damage = (double)rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        damage += ability(damage);
        return damage;
    }

    @Override
    public double defend(double inDamage) {
        Random rand = new Random();
        double defence = (double)rand.nextInt(maxDefence - minDefence + 1) + minDefence;
        return Math.max(0.0, inDamage - defence); //TODO implement health reduction here or in controller?
    }
}
