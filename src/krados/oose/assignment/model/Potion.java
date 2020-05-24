package krados.oose.assignment.model;

import java.util.Random;

public class Potion extends ShopItem {
    private String name;
    private int cost;
    private boolean healing;
    private int minEffect;
    private int maxEffect;

    public Potion(String name, int cost, boolean healing, int minEffect, int maxEffect) {
        super(name, cost);
        this.healing = healing;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    public int use() {
        Random rand = new Random();
        return rand.nextInt(maxEffect - minEffect + 1) + minEffect;
    }
}
