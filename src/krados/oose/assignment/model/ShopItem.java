package krados.oose.assignment.model;

public abstract class ShopItem {
    private String name;
    private int cost;

    public ShopItem(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
} //TODO some way to view details of item, such as the enchantments it has (low priority) (list of strings?)
