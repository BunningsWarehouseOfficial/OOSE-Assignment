package krados.oose.assignment.model;

import java.text.DecimalFormat;

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

        if (min % 1 == 0 && max % 1 == 0) {
            s += (int)min + " - " + (int)max; //Truncate numbers to remove unnecessary clutter
        }
        else {
            DecimalFormat twoDP = new DecimalFormat("#.##");
            s += twoDP.format(min) + " - " + twoDP.format(max); //Prevent unnecessary clutter in String //test
        }
        s += " " + getTypeDamage() + " damage) [Type: " + getType() + "] \n";
        return s;
    }
    @Override
    public String toStringShortened() {
        String s = getName() + " (";
        double min = getMinDamage();
        double max = getMaxDamage();

        if (min % 1 == 0 && max % 1 == 0) { //Check if
            s += (int)min + " - " + (int)max; //Truncate numbers to remove unnecessary clutter
        }
        else {
            DecimalFormat twoDP = new DecimalFormat("#.##");
            s += twoDP.format(min) + " - " + twoDP.format(max); //Prevent unnecessary clutter in String //test
        }
        s += " " + getTypeDamage() + " damage) \n";
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
      /*Given the current design and implementation of the program the method does not make sense, as enchantments are
      * added to weapons, not */
      //TODO write explanatory comment (if I keep it)
        throw new UnsupportedOperationException("Enchantment.givePlayer(Player) has no implementation");
    }
}
