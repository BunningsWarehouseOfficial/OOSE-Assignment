package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

import java.util.Random;

public class WeaponItem implements Weapon {
    private String name;
    private int cost;
    private String type;
    private String typeDamage;
    private int minDamage;
    private int maxDamage;
    private int numEnchantments;

    //CONSTRUCTOR
    public WeaponItem(String name, int cost, String type, String typeDamage, int minDamage, int maxDamage) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.typeDamage = typeDamage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        numEnchantments = 0;
    }

    //ACCESSORS
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getCost() {
        return cost;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public String getTypeDamage() {
        return typeDamage;
    }
    @Override
    public double getMinDamage() {
        return minDamage;
    }
    @Override
    public double getMaxDamage() {
        return maxDamage;
    }
    public int getNumEnchantments() {
        return numEnchantments;
    }

    @Override
    public String toString() {
        return name + " (" + minDamage + " - " + maxDamage + " " + typeDamage + " damage) [Type: " +
               type + "] \n";
    }
    @Override
    public String toStringShortened() {
        return name + " (" + minDamage + " - " + maxDamage + " " + typeDamage + " damage) \n";
    }
    @Override
    public String getSellValue() {
        return "{$} " + cost / 2 + " gold \n";
    }

    //OTHER
    @Override
    public double attack() { //TODO does this even work being in abstract class? Subclass fields don't do anything
        Random rand = new Random();
        return rand.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    @Override
    public void givePlayer(Player p) throws FullInventoryException {
        p.addWeapon(this);
    }
}
