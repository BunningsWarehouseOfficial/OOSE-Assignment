package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;
import krados.oose.assignment.controller.exceptions.ItemNotFoundException;

import java.util.LinkedList;

public class Player extends Entity {
    public static final int INVENTORY_SIZE = 15;
    //private int maxHealth;
    //private double health;
    private String name;
    private LinkedList<Weapon> invWeapons;
    private LinkedList<Armour> invArmours;
    private LinkedList<Potion> invPotions;
    private Weapon weapon;
    private Armour armour;
    private int gold;
    private int numItems;

    //CONSTRUCTORS
    public Player() { //Default Constructor
        super(30);
        this.name = "X Æ A-12";
        invWeapons = new LinkedList<>();
        invArmours = new LinkedList<>();
        invPotions = new LinkedList<>();
        weapon = null; //TODO assign initial weapon and armour in factory
        armour = null;
        gold = 100;
        numItems = 0;
    }
    public Player(int maxHealth, String name, int startingGold) { //Alternate Constructor
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
    public Weapon getEquippedWeapon() {
        return weapon;
    }
    public Armour getEquippedArmour() {
        return armour;
    }
    public Weapon getWeapon(int index) {
        return invWeapons.get(index);
    }
    public Armour getArmour(int index) {
        return invArmours.get(index);
    }
    public int getGold() {
        return gold;
    }
    public int getNumItems() {
        return numItems;
    }

    //MUTATORS
    public void setName(String name) {
        this.name = name;
    }
    public void addWeapon(Weapon weapon) throws FullInventoryException {
        if (numItems < 15) {
            invWeapons.addLast(weapon);
            numItems++;
        }
        else {
            throw new FullInventoryException("The player inventory is full");
        }
    }
    public void addArmour(Armour armour) throws FullInventoryException {
        if (numItems < 15) {
            invArmours.addLast(armour);
            numItems++;
        }
        else {
            throw new FullInventoryException("The player inventory is full");
        }
    }
    public void addPotion(Potion potion) throws FullInventoryException {
        if (numItems < 15) {
            invPotions.addLast(potion);
            numItems++;
        }
        else {
            throw new FullInventoryException("The player inventory is full");
        }
    }
    public void equipWeapon(Weapon weapon) throws ItemNotFoundException {
        if (invWeapons.contains(weapon)) {
            if (this.weapon != null) { //If there is a weapon currently equipped, re-add it to the inventory list
                invWeapons.addFirst(this.weapon);
            }
            invWeapons.remove(weapon); //The equipped weapon is not stored in the inventory list
            this.weapon = weapon;
        }
        else if (numItems < 2) { //Occurs at game start, where player is automatically assigned their first weapon
            this.weapon = weapon;
            numItems++;
        }
        else {
            throw new ItemNotFoundException("Player does not have the specified weapon");
        }
    }
    public void equipArmour(Armour armour) throws ItemNotFoundException {
        if (invArmours.contains(armour)) {
            if (this.armour != null) { //If there is armour currently equipped, re-add it to the inventory list
                invArmours.addFirst(this.armour);
            }
            invArmours.remove(armour); //The equipped armour is not stored in the inventory list
            this.armour = armour;
        }
        else if (numItems < 2) { //Occurs at game start, where player is automatically assigned their first armour
            this.armour = armour;
            numItems++;
        }
        else {
            throw new ItemNotFoundException("Player does not have the specified armour");
        }
    }
    public int sellItem(int index) throws ItemNotFoundException {
        ShopItem item;
        int adjustedIndex = index; //Index relative to the inventory container being searched at each step
        int value = 0;

        if (adjustedIndex < invWeapons.size() && index >= 0) {
            item = invWeapons.get(adjustedIndex);
            value = sell(item);
            invWeapons.remove(adjustedIndex);
        }
        else {
            adjustedIndex -= invArmours.size();
            if (adjustedIndex < invArmours.size()) {
                item = invArmours.get(adjustedIndex);
                value = sell(item);
                invArmours.remove(adjustedIndex);
            }
            else {
                adjustedIndex -= invPotions.size();
                if (adjustedIndex < invPotions.size()) {
                    item = invPotions.get(adjustedIndex);
                    value = sell(item);
                    invPotions.remove(adjustedIndex);
                }
                else {
                   throw new ItemNotFoundException("Could not find an item at the specified index in player inventory");
                }
            }
        }
        return value;
    }

    @Override
    public double attack() {

        return 0.0;
    }
    @Override
    public double defend(double inDamage) {

        return 0;
    }

    //PRIVATE
    private int sell(ShopItem item) {
        int value = item.getCost() / 2;
        gold += value;
        numItems--;

        return value;
    }
}
