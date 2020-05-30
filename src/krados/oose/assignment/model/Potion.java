package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

import java.util.Random;

public class Potion implements ShopItem {
    private String name;
    private int cost;
    private boolean healing;
    private int minEffect;
    private int maxEffect;

    //CONSTRUCTOR
    public Potion(String name, int cost, boolean healing, int minEffect, int maxEffect) {
        this.name = name;
        this.cost = cost;
        this.healing = healing;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    //ACCESSORS
    public String getName() {
        return name;
    }
    @Override
    public int getCost() {
        return cost;
    }
    public boolean isHealing() {
        return healing;
    }
    @Override
    public String toString() {
        String s = name + " (" + minEffect + " - " + maxEffect;
        if (healing) {
            s += " healing) \n";
        }
        else {
            s += " damage) \n";
        }
        return s;
    }
    @Override
    public String toStringShortened() { return toString(); } //Potion has no extra details, thus no shortened version
    @Override
    public String getSellValue() {
        return "{$} " + cost / 2 + " gold \n";
    }

    //OTHER
    public int use() {
        Random rand = new Random();
        return rand.nextInt(maxEffect - minEffect + 1) + minEffect;
    }

    @Override
    public void givePlayer(Player p) throws FullInventoryException {
        p.addPotion(this);
    }
}
