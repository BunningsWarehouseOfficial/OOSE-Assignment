package krados.oose.assignment.model;

public abstract class Entity {
    private int maxHealth;
    private double health;

    //CONSTRUCTOR
    public Entity(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    //ACCESSORS
    public int getMaxHealth() {
        return maxHealth;
    }
    public double getHealth() {
        return health;
    }

    //MUTATORS
    public void setHealth(double health) {
        this.health = health;
    }

    //OTHER
    abstract public double attack();
    abstract public double defend(double inDamage);
}
