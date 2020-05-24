package krados.oose.assignment.model;

import java.util.Random;

public class Weapon extends ShopItem implements Enchantable {
    private String name;
    private int cost;
    private String type;
    private String typeDamage;
    private int minDamage;
    private int maxDamage;

    //TODO input validation: here or in ShopController?
    public Weapon(String name, int cost, String type, String typeDamage, int minDamage, int maxDamage) {
        super(name, cost);
        this.type = type;
        this.typeDamage = typeDamage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public double attack() { //TODO does this even work being in abstract class? Subclass fields don't do anything
        Random rand = new Random();
        return rand.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
}
