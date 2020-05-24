package krados.oose.assignment.model;

public class Damage5 extends Enchantment {
    public Damage5(Enchantable next) {
        super("Damage +5", 10);
        this.next = next;
    }

    @Override
    public double attack() {
        return next.attack() + 5.0;
    }
}
