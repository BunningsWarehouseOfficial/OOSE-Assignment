package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

import java.util.Random;

public class Potion extends ShopItem {
    private boolean healing;
    private int minEffect;
    private int maxEffect;

    //CONSTRUCTOR
    public Potion(String name, int cost, boolean healing, int minEffect, int maxEffect) {
        super(name, cost);
        this.healing = healing;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    //ACCESSORS
    public boolean isHealing() {
        return healing;
    }
    public int getMinEffect() {
        return minEffect;
    }
    public int getMaxEffect() {
        return maxEffect;
    }

    @Override
    public String toString() {
        String s = getName() + " (" + minEffect + " - " + maxEffect;
        if (healing) {
            s += " healing) \n";
        }
        else {
            s += " damage) \n";
        }
        return s;
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
