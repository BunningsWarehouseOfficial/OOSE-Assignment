package krados.oose.assignment.model;

import java.util.Random;

public abstract class Enemy extends Entity {
    private int minDamage; //TODO does maxHealth and health need to be here? Seems like I don't
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int reward;

    //CONSTRUCTOR
    public Enemy(int maxHealth, int minDamage, int maxDamage, int minDefence, int maxDefence, int reward) {
        super(maxHealth);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.reward = reward;
    }

    //FACTORY
    public static Enemy makeEnemy(int numBattles) {
        double pSlime, pGoblin, pOgre, pDragon, prob, modifier, cumulative;
        Enemy enemy;
        prob = Math.random();

        modifier = 0.05;
        pSlime = Math.max(0.05, Slime.INITIAL_PROB - (numBattles * modifier));
        pGoblin = Math.max(0.05, Goblin.INITIAL_PROB - (numBattles * modifier));
        pOgre = Math.max(0.05, Ogre.INITIAL_PROB - (numBattles * modifier));

        if (prob < pSlime) {
            enemy = new Slime();
        }
        else if (prob < pSlime + pGoblin) {
            enemy = new Goblin();
        }
        else if (prob < pSlime + pGoblin + pOgre) {
            enemy = new Ogre();
        }
        else {
            enemy = new Dragon();
        }
        return enemy;
    }

    abstract public double ability(double damage);

    @Override
    public double attack() { //TODO does this even work being in abstract class? Subclass fields don't do anything
        Random rand = new Random();
        double damage = (double)rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        damage += ability(damage);
        return damage;
    }
    @Override
    public double defend(double inDamage) {
        Random rand = new Random();
        double defence = (double)rand.nextInt(maxDefence - minDefence + 1) + minDefence;
        return Math.max(0.0, inDamage - defence); //TODO implement health reduction here or in controller?
    }
}
