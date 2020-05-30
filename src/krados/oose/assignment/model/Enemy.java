package krados.oose.assignment.model;

import java.util.Random;

public abstract class Enemy extends Entity {
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private String usedAbility;
    private int reward;

    //CONSTRUCTOR
    public Enemy(int maxHealth, int minDamage, int maxDamage, int minDefence, int maxDefence, int reward) {
        super(maxHealth);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        usedAbility = null;
        this.reward = reward;
    }

    //ACCESSORS
    public int getMinDamage() {
        return minDamage;
    }
    public int getMaxDamage() {
        return maxDamage;
    }
    public String getUsedAbility() {
        return usedAbility;
    }
    public int getReward() {
        return reward;
    }
    public abstract String getSpecies();

    //MUTATORS
    public void setUsedAbility(String usedAbility) {
        this.usedAbility = usedAbility;
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
    public double attack() {
        Random rand = new Random();
        double damage = (double)rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        return damage;
    }
    @Override
    public double defend(double inDamage) {
        Random rand = new Random();
        double defence = (double)rand.nextInt(maxDefence - minDefence + 1) + minDefence;
        double reducedDamage = Math.max(0.0, inDamage - defence); //Calculating the reduced damage
        setHealth(getHealth() - reducedDamage); //Lowering enemy health by the reduced damage
        return defence; //Return the amount of damage that was defended
    }
}
