package krados.oose.assignment.model;

public class Damage2 extends Enchantment {
    public Damage2(Enchantable next) {
        super("Damage +2", 5);
        this.next = next;
    }

    @Override
    public double getMinDamage() {
        return next.getMinDamage() + 2.0;
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage() + 2.0;
    }

    @Override
    public double attack() {
        return next.attack() + 2.0;
    }
}
