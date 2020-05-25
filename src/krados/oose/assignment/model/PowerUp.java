package krados.oose.assignment.model;

public class PowerUp extends Enchantment {
    public static double POWER_UP = 1.1;

    public PowerUp(Enchantable next) {
        super("Power-Up", 10);
        this.next = next;
    }

    @Override
    public double getMinDamage() {
        return next.getMinDamage() * POWER_UP;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() * POWER_UP;
    }

    @Override
    public double attack() {
        return next.attack() * POWER_UP;
    }
}
