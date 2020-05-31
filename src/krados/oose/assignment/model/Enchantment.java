package krados.oose.assignment.model;

import krados.oose.assignment.controller.Controller;

public abstract class Enchantment implements Weapon {
    protected Weapon next;

    //CONSTRUCTOR
    public Enchantment(Weapon next) {
        this.next = next;
    }

    //ACCESSORS
    @Override
    public String getName() {
        return next.getName();
    }
    @Override
    public String getTypeDamage() {
        return next.getTypeDamage();
    }
    @Override
    public String getType() {
        return next.getType();
    }
    @Override
    public int getNumEnchantments () {
        return next.getNumEnchantments() + 1;
    }

    @Override
    public String toString() {
        String s = getName() + " (";
        double min = getMinDamage();
        double max = getMaxDamage();

        s += Controller.cleanDouble(min) + " - " + Controller.cleanDouble(max) + " " + getTypeDamage() +
             " damage) [Type: " + getType() + "] \n";
        return s;
    }
    @Override
    public String toStringShortened() {
        String s = getName() + " (";
        double min = getMinDamage();
        double max = getMaxDamage();

        s += Controller.cleanDouble(min) + " - " + Controller.cleanDouble(max) + " " + getTypeDamage() + " damage) \n";
        return s;
    }
    @Override
    public String getSellValue() {
        return next.getSellValue();
    }

    //OTHER
    @Override
    public double attack() {
        return next.attack();
    }
    @Override
    public void givePlayer(Player p) {
      /*Given the current design and implementation of the program this method does not make sense, as enchantments are
        added to weapons, not the player. However, a future modification may require adding enchantments to inventory*/
        throw new UnsupportedOperationException("Enchantment.givePlayer(Player) has no implementation");
    }
}
