package krados.oose.assignment.model;

public class Damage5 extends Enchantment {
    public static int COST = 10;

    //CONSTRUCTOR
    public Damage5(Weapon next) {
        super(next);
    }

    //ACCESSORS
    @Override
    public int getCost() {
        return next.getCost() + COST;
    }
    @Override
    public double getMinDamage() {
        return next.getMinDamage() + 5.0;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() + 5.0;
    }

    //OTHER
    @Override
    public double attack() {
        return next.attack() + 5.0;
    }
}
