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

    abstract public int ability(int damage);

    public double attack() { //TODO does this even work being in abstract class? Subclass fields don't do anything
        Random rand = new Random();
        int damage = rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        damage += ability(damage);
        return damage;
    }

    public int defend(int inDamage) { //TODO does this even work being in abstract? Subclass fields don't do anything
        Random rand = new Random();
        int defence = rand.nextInt(maxDefence - minDefence + 1) + minDefence;
        return Math.max(0, inDamage - defence); //TODO implement health reduction here or in controller?
    }
}
