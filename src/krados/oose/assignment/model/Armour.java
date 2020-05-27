package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

import java.util.Random;

public class Armour extends ShopItem {
    private String material;
    private int minDefence;
    private int maxDefence;

    //CONSTRUCTOR
    public Armour(String name, int cost, String material, int minDefence, int maxDefence) {
        super(name, cost);
        this.material = material;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    //ACCESSORS
    public String getMaterial() {
        return material;
    }
    public int getMinDefence() {
        return minDefence;
    }
    public int getMaxDefence() {
        return maxDefence;
    }

    @Override
    public String toString() {
        return getName() + " (" + minDefence + " - " + maxDefence + " defence) [Material: " + material + "] \n";
    }
    public String toStringShortened() {
        return getName() + " (" + minDefence + " - " + maxDefence + " defence) \n";
    }

    //OTHER
    public int defend(int inDamage) { //TODO does this even work being in abstract? Subclass fields don't do anything
        Random rand = new Random();
        int defence = rand.nextInt(maxDefence - minDefence + 1) + minDefence;
        return Math.max(0, inDamage - defence); //TODO implement health reduction here or in controller?
    }


    @Override
    public void givePlayer(Player p) throws FullInventoryException {
        p.addArmour(this);
    }
}
