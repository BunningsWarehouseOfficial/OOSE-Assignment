package krados.oose.assignment.model;

public class Slime extends Enemy {
    private double maxHealth;
    private double health;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int reward;

    //TODO perhaps modify these constructors to not be hardcoded and take in values in a factory?
    public Slime() {
        super(10, 3, 5, 0, 2, 10);
    }

    @Override
    public int ability(int damage) {
        int outDamage = damage;
        if (Math.random() < 0.2) {
            outDamage = 0;
            //TODO signal view ability was used
        }
        return outDamage;
    }
}
