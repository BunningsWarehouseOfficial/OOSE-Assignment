package krados.oose.assignment.model;

public abstract class Enchantment extends ShopItem implements Enchantable {
    //Implement all the interface methods fully here so that not every method
    //has to be implemented again for every single concrete class

    private String name;
    private int cost;
    protected Enchantable next;

    public Enchantment(String name, int cost) {
        super(name, cost);
        next = null;
    }

    @Override
    public double attack() {
        return next.attack();
    }
}
