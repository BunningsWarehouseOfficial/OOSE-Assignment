package krados.oose.assignment.model;

public class Goblin extends Enemy {
    public static final double INITIAL_PROB = 0.3;

    //CONSTRUCTOR
    public Goblin() {
        super(30, 3, 8, 4, 8, 20);
    }

    //ACCESSORS
    public String getSpecies() {
        return "Goblin";
    }

    //OTHER
    @Override
    public double ability(double damage) {
        double outDamage = damage;
        if (Math.random() < 0.5) {
            outDamage += 3.0;
            setUsedAbility("Extra Damage (+ 3 damage)");
        }
        return outDamage;
    }
}
