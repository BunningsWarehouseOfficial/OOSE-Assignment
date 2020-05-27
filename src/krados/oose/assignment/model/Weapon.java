package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

import java.util.Random;

public class Weapon extends ShopItem implements Enchantable {
    private String type;
    private String typeDamage;
    private int minDamage;
    private int maxDamage;
    private int numEnchantments;

    //CONSTRUCTOR //TODO input validation: here or in ShopController?
    public Weapon(String name, int cost, String type, String typeDamage, int minDamage, int maxDamage) {
        super(name, cost);
        this.type = type;
        this.typeDamage = typeDamage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    //ACCESSORS
    public String getType() {
        return type;
    }
    public String getTypeDamage() {
        return typeDamage;
    }
    public double getMinDamage() {
        return minDamage;
    }
    public double getMaxDamage() {
        return maxDamage;
    }
    public int getNumEnchantments() {
        return numEnchantments;
    }

    @Override
    public String toString() {
        return getName() + " (" + minDamage + " - " + maxDamage + " " + typeDamage + " damage) [Type: " + type + "] \n";
    }
    public String toStringShortened() {
        return getName() + " (" + minDamage + " - " + maxDamage + " " + typeDamage + " damage) \n";
    }

    //MUTATORS
    public void enchant() {


        numEnchantments++;
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
