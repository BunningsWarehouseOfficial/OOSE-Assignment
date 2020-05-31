package krados.oose.assignment.model;

public class PowerUp extends Enchantment {
    public static final int COST = 10;
    public static final double MULTIPLIER = 1.1;

    //CONSTRUCTOR
    public PowerUp(Weapon next) {
        super(next);
    }

    //ACCESSORS
    @Override
    public int getCost() {
        return next.getCost() + COST;
    }
    @Override
    public double getMinDamage() {
        return next.getMinDamage() * MULTIPLIER;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() * MULTIPLIER;
    }

    //OTHER
    @Override
    public double attack() {
        return next.attack() * MULTIPLIER;
    }
}
