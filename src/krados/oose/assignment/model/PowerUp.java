package krados.oose.assignment.model;

public class PowerUp extends Enchantment {
    public PowerUp(Enchantable next) {
        super("Power-Up", 10);
        this.next = next;
    }

    @Override
    public double attack() {
        return next.attack() * 1.1;
    }
}
