package krados.oose.assignment.model;

public abstract class ShopItem {
    private String name;
    private int cost;

    public ShopItem(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}
