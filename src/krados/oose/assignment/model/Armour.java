package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

import java.util.Random;

public class Armour implements ShopItem {
    private String name;
    private int cost;
    private String material;
    private int minDefence;
    private int maxDefence;

    //CONSTRUCTOR
    public Armour(String name, int cost, String material, int minDefence, int maxDefence) {
        this.name = name;
        this.cost = cost;
        this.material = material;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    //ACCESSORS
    @Override
    public int getCost() {
        return cost;
    }
    @Override
    public String toString() {
        return name + " (" + minDefence + " - " + maxDefence + " defence) [Material: " + material + "] \n";
    }
    @Override
    public String toStringShortened() {
        return name + " (" + minDefence + " - " + maxDefence + " defence) \n";
    }
    @Override
    public String getSellValue() {
        return "{$} " + cost / 2 + " gold \n";
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
