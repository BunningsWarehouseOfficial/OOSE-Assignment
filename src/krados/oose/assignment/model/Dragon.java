package krados.oose.assignment.model;

public class Dragon extends Enemy {
    //CONSTRUCTOR
    public Dragon() {
        super(100, 15, 30, 15, 20, 100);
    }

    //ACCESSORS
    public String getSpecies() {
        return "Dragon";
    }

    //OTHER
    @Override
    public double ability(double damage) {
        double outDamage = damage;
        double prob = Math.random();
        if (prob < 0.25) {
            outDamage *= 2.0;
            //TODO signal view ability was used
        }
        else if (prob < 0.1) {
            double newHealth = Math.max(getMaxHealth(), getHealth() + 10.0);
            setHealth(newHealth);
            //TODO signal view ability was used
        }
        return outDamage;
    }
}
