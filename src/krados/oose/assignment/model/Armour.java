package krados.oose.assignment.model;

import java.util.Random;

public class Armour extends ShopItem {
    private String name;
    private int cost;
    private String material;
    private int minDefence;
    private int maxDefence;

    public Armour(String name, int cost, String material, int minDefence, int maxDefence) {
        super(name, cost);
        this.material = material;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    public String getMaterial() {
        return material;
    }
    public int getMinDefence() {
        return minDefence;
    }
    public int getMaxDefence() {
        return maxDefence;
    }

    public int defend(int inDamage) { //TODO does this even work being in abstract? Subclass fields don't do anything
        Random rand = new Random();
        int defence = rand.nextInt(maxDefence - minDefence + 1) + minDefence;
        return Math.max(0, inDamage - defence); //TODO implement health reduction here or in controller?
    }
}
