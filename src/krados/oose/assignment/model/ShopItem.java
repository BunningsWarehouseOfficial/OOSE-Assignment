package krados.oose.assignment.model;

import krados.oose.assignment.controller.exceptions.FullInventoryException;

public interface ShopItem {
    int getCost();
    String getSellValue();
    String toStringShortened();

    void givePlayer(Player p) throws FullInventoryException;
}
