package krados.oose.assignment.model;

public class Goblin extends Enemy {
    private double maxHealth;
    private double health;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int reward;

    public Goblin() {
        super(30, 3, 8, 4, 8, 20);
    }

    @Override
    public double ability(double damage) {
        double outDamage = damage;
        if (Math.random() < 0.5) {
            outDamage += 3.0;
            //TODO signal view ability was used
        }
        return outDamage;
    }
}