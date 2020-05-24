package krados.oose.assignment.model;

public class Weapon implements Item, Enchantable {
    private String name;
    private int cost;
    private String type;
    private String typeDamage;
    private int minDamage;
    private int maxDamage;

    @Override
    public double attack() {

        return 0;
    }
}
