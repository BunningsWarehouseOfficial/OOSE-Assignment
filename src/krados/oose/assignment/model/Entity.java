package krados.oose.assignment.model;

public abstract class Entity {
    private int maxHealth;
    private double health;

    //CONSTRUCTOR
    public Entity(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    abstract public double attack();
    abstract public double defend(double inDamage);
}
