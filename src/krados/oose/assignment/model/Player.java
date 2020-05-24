package krados.oose.assignment.model;

import java.util.LinkedList;

public class Player extends Entity {
    private int maxHealth;
    private double health;
    private String name;
    private LinkedList<Item> inventory;
    private Weapon weapon;
    private Armour armour;
    private int gold;
    private int numItems;

    public Player(int maxHealth, String name, int startingGold) {
        super(maxHealth);
        this.name = name;
        inventory = new LinkedList<>();
        weapon = null; //TODO assign initial weapon and armour in factory
        armour = null;
        gold = startingGold;
        numItems = 0;
    }

    @Override
    public double attack() {

        return 0.0;
    }

    @Override
    public int defend(int inDamage) {

        return 0;
    }
}
