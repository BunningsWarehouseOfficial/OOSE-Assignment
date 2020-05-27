package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

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
    public String getSellValue() {
        return "{$} " + cost / 2 + " gold \n";
    }

    public abstract void givePlayer(Player p) throws FullInventoryException;
}
