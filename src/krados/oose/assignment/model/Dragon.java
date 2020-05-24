package krados.oose.assignment.model;

public class Dragon extends Enemy {
    private double maxHealth;
    private double health;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int reward;

    public Dragon() {
        super(100, 15, 30, 15, 20, 100);
    }

    @Override
    public double ability(double damage) {
        double outDamage = damage;
        double prob = Math.random();
        if (prob < 0.25) {
            outDamage *= 2.0;
            //TODO signal view ability was used
        }
        else if (prob < 0.1) {
            health = Math.max(maxHealth, health + 10.0);
            //TODO signal view ability was used
        }
        return outDamage;
    }
}
