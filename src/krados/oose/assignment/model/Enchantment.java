package krados.oose.assignment.model;

public abstract class Enchantment implements Item {
    //Implement all the interface methods fully here so that not every method
    //has to be implemented again for every single concrete class

    private String name;
    private int cost;
    protected Item next;
}
