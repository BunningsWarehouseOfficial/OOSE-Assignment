package krados.oose.assignment.model;

public class Damage2 extends Enchantment {
    public static int COST = 5;

    //CONSTRUCTOR
    public Damage2(Weapon next) {
        super(next);
    }

    //ACCESSORS
    @Override
    public int getCost() {
        return next.getCost() + COST;
    }
    @Override
    public double getMinDamage() {
        return next.getMinDamage() + 2.0;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() + 2.0;
    }

    //OTHER
    @Override
    public double attack() {
        return next.attack() + 2.0;
    }
}
