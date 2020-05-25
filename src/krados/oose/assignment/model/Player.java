package krados.oose.assignment.model;

import java.util.LinkedList;

public class Player extends Entity {
    public static final int INVENTORY_SIZE = 15;
    private int maxHealth;
    private double health;
    private String name;
    private LinkedList<Weapon> invWeapons;
    private LinkedList<Armour> invArmours;
    private LinkedList<Potion> invPotions;
    private Weapon weapon;
    private Armour armour;
    private int gold;
    private int numItems;

    //CONSTRUCTOR
    public Player(int maxHealth, String name, int startingGold) {
        super(maxHealth);
        this.name = name;
        invWeapons = new LinkedList<>();
        invArmours = new LinkedList<>();
        invPotions = new LinkedList<>();
        weapon = null; //TODO assign initial weapon and armour in factory
        armour = null;
        gold = startingGold;
        numItems = 0;
    }

    //ACCESSORS
    public int getMaxHealth() {
        return maxHealth;
    }
    public double getHealth() {
        return health;
    }
    public String getName() {
        return name;
    }
    public LinkedList<Weapon> getInvWeapons() {
        return invWeapons;
    }
    public LinkedList<Armour> getInvArmours() {
        return invArmours;
    }
    public LinkedList<Potion> getInvPotions() {
        return invPotions;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public Armour getArmour() {
        return armour;
    }
    public int getGold() {
        return gold;
    }
    public int getNumItems() {
        return numItems;
    }

    @Override
    public double attack() {

        return 0.0;
    }
    @Override
    public double defend(double inDamage) {

        return 0;
    }
}
