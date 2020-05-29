package krados.oose.assignment.model;

public class Slime extends Enemy {
    public static double INITIAL_PROB = 0.5;

    //TODO perhaps modify these constructors to not be hardcoded and take in values in a factory?
    //CONSTRUCTOR
    public Slime() {
        super(10, 3, 5, 0, 2, 10);
    }

    //ACCESSORS
    public String getSpecies() {
        return "Slime";
    }

    //OTHER
    @Override
    public double ability(double damage) {
        double outDamage = damage;
        if (Math.random() < 0.2) {
            outDamage = 0.0;
            //TODO signal view ability was used
        }
        return outDamage;
    }
}
