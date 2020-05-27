package krados.oose.assignment.model;

public abstract class Enchantment extends ShopItem implements Enchantable {
    //Implement all the interface methods fully here so that not every method
    //has to be implemented again for every single concrete class

    private String name;
    private int cost;
    protected Enchantable next;

    //CONSTRUCTOR
    public Enchantment(String name, int cost) {
        super(name, cost);
        next = null;
    }

    //ACCESSORS
    @Override
    public double getMinDamage() {
        return next.getMinDamage();
    }
    @Override
    public double getMaxDamage() {
        return next.getMaxDamage();
    }

    @Override
    public double attack() {
        return next.attack();
    }
    @Override
    public void givePlayer(Player p) {
      /*Given the current design and implementation of the program the method does not make sense, as enchantments are
      * added to weapons, not */
      //TODO write explanatory comment
        throw new UnsupportedOperationException("Enchantment.givePlayer(Player) has no implementation");
    }
}
